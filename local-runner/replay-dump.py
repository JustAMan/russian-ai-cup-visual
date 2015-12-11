#pylint: disable=missing-docstring, invalid-name
import urllib
import re
import sys
import contextlib
import os
import errno
import subprocess

RUN_PLAYER_RE = re.compile(r'''<span\s+class\s*=\s*["']?run-player["']?\s*(.*)>''')
TOKEN_RE = re.compile(r'''.*data-token\s*=\s*["']?([^'"\s]+)['"]?''')
CONTENT_LENGTH_RE = re.compile(r'Content-Length:\s*(\d+)')
CHUNK = 1 * 1024 * 1024

def readPage(url):
    with contextlib.closing(urllib.urlopen(url)) as target:
        headers = target.info().headers
        for header in headers:
            try:
                size = int(CONTENT_LENGTH_RE.search(header).group(1))
            except AttributeError:
                continue
            else:
                break
        else:
            size = None
        readBytes = 0
        if size is not None and size >= CHUNK:
            print 'Downloading: 00.0%',
            while readBytes < size:
                chunk = target.read(CHUNK)
                readBytes += len(chunk)
                print '\rDownloading: %4.1f%%' % (100.0 * readBytes / size),
                yield chunk
        else:
            print 'Downloading: ',
            while True:
                chunk = target.read(CHUNK)
                if not chunk:
                    break
                readBytes += len(chunk)
                print '\rDownloading: %0.1fM' % (readBytes / 1024.0 / 1024),
                yield chunk
        print '\rDownloading: done%s' % (' ' * 10)

def getReplayAddress(gameUrl):
    with contextlib.closing(urllib.urlopen(gameUrl)) as gamePage:
        game = gamePage.read()
    runPlayerSpan = RUN_PLAYER_RE.search(game).group(1)
    token = TOKEN_RE.search(runPlayerSpan).group(1)
    return 'http://russianaicup.ru/boombox/data/games/%s?offset=0' % token

def downloadReplay(replayUrl, targetPath):
    with open(targetPath, 'w') as out:
        for chunk in readPage(replayUrl):
            out.write(chunk)

def ensureReplay(gameUrl):
    targetFile = os.path.join('games', gameUrl.split('/')[-1] + '.json')
    if os.path.exists(targetFile):
        print 'File already downloaded'
        return targetFile

    replayUrl = getReplayAddress(gameUrl)
    try:
        downloadReplay(replayUrl, targetFile)
        print 'Downloaded to: %s' % targetFile
    except KeyboardInterrupt:
        print 'Interrupted, removing partial file'
        os.remove(targetFile)
        return None

    return targetFile

def ensureGameUrl(gameUrl):
    try:
        gameNumber = int(gameUrl.strip())
    except ValueError:
        return gameUrl
    return 'http://russianaicup.ru/game/view/%d' % gameNumber

def main(gameUrl):
    os.chdir(os.path.dirname(os.path.abspath(__file__)))
    try:
        os.makedirs('games')
    except OSError as ex:
        if ex.errno != errno.EEXIST:
            raise

    replayFile = ensureReplay(gameUrl)
    if not replayFile:
        sys.exit('Cannot run replay')
    props = []
    with open('local-runner-replay.properties', 'r') as inp:
        for line in inp:
            if line.startswith('replay-file='):
                props.append('replay-file=%s\n' % replayFile.replace('\\', '/'))
            else:
                props.append(line)
    with open('local-runner-replay.properties', 'w') as out:
        out.write(''.join(props))
    subprocess.check_call(['java', '-jar', "local-runner.jar", 'local-runner-replay.properties'], shell=False)

if __name__ == '__main__':
    if len(sys.argv) != 2:
        sys.exit('Usage: %s game-url-or-number' % sys.argv[0])
    main(ensureGameUrl(sys.argv[1]))
