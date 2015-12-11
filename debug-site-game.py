import sys
import os
import subprocess
import time
import atexit

REPEATER_SUBDIR = 'repeater'

def startRepeater(token):
    cwd = os.getcwd()
    try:
        os.chdir(REPEATER_SUBDIR)
        repeater = subprocess.Popen(['java', '-cp', '.;*;%s/*' % os.getcwd(), '-jar',
                                     'repeater.jar', token], shell=False)
        time.sleep(0.5)
    finally:
        os.chdir(cwd)

    def repeaterStop(proc=repeater):
        if proc.poll() is None:
            proc.kill()
    atexit.register(repeaterStop)

def main(gameNumber, repeaterToken):
    os.chdir(os.path.abspath(os.path.dirname(__file__)))
    if not startRepeater(repeaterToken):
        sys.exit('cannot start repeater')
    runner = subprocess.Popen([sys.executable, os.path.abspath('local-runner/replay-dump.py'),
                               gameNumber], shell=False)
    runner.wait()

if __name__ == '__main__':
    try:
        gameNumber, repeaterToken = sys.argv[1:]
    except ValueError:
        sys.exit('Usage: %s game-number repeater-token' % sys.argv[0])
    else:
        main(gameNumber, repeaterToken)
