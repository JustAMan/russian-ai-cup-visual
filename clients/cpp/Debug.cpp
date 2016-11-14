#include "Debug.h"
#if (defined _WIN32 || defined _WIN64)
# include <winsock2.h>
# include <Ws2tcpip.h>

#include <BaseTsd.h>
typedef SSIZE_T ssize_t;

namespace {

	ssize_t close(SOCKET s)
	{
		return closesocket(s);
	}

	ssize_t write(SOCKET s, const char *buf, int len, int flags = 0)
	{
		return send(s, buf, len, flags);
	}

}

#pragma warning(disable: 4244 4996)
#else
# include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#endif
#include <cstdio>
#include <cstdlib>

#include <string>

std::string Debug::DEFAULT_HOST = "127.0.0.1";
std::string Debug::DEFAULT_PORT = "13579";
const int Debug::BUF_SIZE = 1024;

Debug::Debug()
	: sfd(-1)
{
	struct addrinfo hints;
	struct addrinfo *result, *rp;
	int s;

	/* Obtain address(es) matching host/port */

	memset(&hints, 0, sizeof(struct addrinfo));
	hints.ai_family = AF_UNSPEC;    /* Allow IPv4 or IPv6 */
	hints.ai_socktype = SOCK_STREAM; /* Datagram socket */
	hints.ai_flags = 0;
	hints.ai_protocol = 0;          /* Any protocol */

	s = getaddrinfo(DEFAULT_HOST.c_str(), DEFAULT_PORT.c_str(), &hints, &result);
	if (s != 0)
	{
		fprintf(stderr, "Could not get address");
		return;
	}

	for (rp = result; rp != NULL; rp = rp->ai_next) {
	   sfd = socket(rp->ai_family, rp->ai_socktype,
					rp->ai_protocol);
	   if (sfd == -1)
		   continue;

	  if (connect(sfd, rp->ai_addr, rp->ai_addrlen) != -1)
		   break;                  /* Success */

	  close(sfd);
	}

	if (rp == NULL) {               /* No address succeeded */
        fprintf(stderr, "Could not connect\n");
		sfd = -1;
		return;
    }
	freeaddrinfo(result);
}

void Debug::sendCommand(const char* str)
{
	if (sfd == -1)
		return;
	int len = strlen(str);
	int pos = 0;
	while (pos < len)
	{
		ssize_t res = write(sfd, str + pos, len);
		if (res == -1)
		{
			fprintf(stderr, "Couldn't send command");
			return;
		}
		pos += res;
	}
}

void Debug::beginPre()
{
	sendCommand("begin pre\n");
}

void Debug::endPre()
{
	sendCommand("end pre\n");
}

void Debug::beginPost()
{
	sendCommand("begin post\n");
}

void Debug::endPost()
{
	sendCommand("end post\n");
}

void Debug::beginAbs()
{
	sendCommand("begin abs\n");
}

void Debug::endAbs()
{
	sendCommand("end abs\n");
}

void Debug::writeWithColor(char* buf, int32_t color)
{
	size_t len = strlen(buf);
	float r = ((color & 0xFF0000) >> 16) / 256.0;
	float g = ((color & 0x00FF00) >> 8) / 256.0;
	float b = ((color & 0x0000FF)) / 256.0;
	sprintf(buf + len, " %f %f %f\n", r, g, b);
	sendCommand(buf);
}

void Debug::circle(double x, double y, double r, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "circle %lf %lf %lf", x, y, r);
	writeWithColor(buf, color);
}

void Debug::fillCircle(double x, double y, double r, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "fill_circle %lf %lf %lf", x, y, r);
	writeWithColor(buf, color);
}

void Debug::rect(double x1, double y1, double x2, double y2, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "rect %lf %lf %lf %lf", x1, y1, x2, y2);
	writeWithColor(buf, color);
}

void Debug::fillRect(double x1, double y1, double x2, double y2, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "fill_rect %lf %lf %lf %lf", x1, y1, x2, y2);
	writeWithColor(buf, color);
}

void Debug::arc(double x, double y, double r, double start_angle, double arc_angle, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "arc %lf %lf %lf %lf %lf", x, y, r, start_angle, arc_angle);
	writeWithColor(buf, color);
}

void Debug::fillArc(double x, double y, double r, double start_angle, double arc_angle, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "fill_arc %lf %lf %lf %lf %lf", x, y, r, start_angle, arc_angle);
	writeWithColor(buf, color);
}

void Debug::line(double x1, double y1, double x2, double y2, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "line %lf %lf %lf %lf", x1, y1, x2, y2);
	writeWithColor(buf, color);
}

void Debug::text(double x, double y, const char* text, int32_t color)
{
	char buf[BUF_SIZE];
	sprintf(buf, "text %lf %lf %s", x, y, text);
	writeWithColor(buf, color);
}
