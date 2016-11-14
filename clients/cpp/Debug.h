#ifndef VISUAL_DEBUG_CLIENT_
#define VISUAL_DEBUG_CLIENT_

#include <cstdlib>
#include <cstdio>
#include <string>
#include <cstdint>

class Debug
{
public:
	static std::string DEFAULT_HOST;
	static std::string DEFAULT_PORT;
	static const int BUF_SIZE;

	Debug();
	void beginPre();
	void endPre();
	void beginPost();
	void endPost();
	void beginAbs();
	void endAbs();
	void circle(double x, double y, double r, int32_t color = 0xFF0000);
	void fillCircle(double x, double y, double r, int32_t color = 0xFF0000);
	void rect(double x1, double y1, double x2, double y2, int32_t color = 0x00FF00);
	void fillRect(double x1, double y1, double x2, double y2, int32_t color = 0x00FF00);
	void arc(double x, double y, double r, double start_angle, double arc_angle, int32_t color = 0xFFFF00);
	void fillArc(double x, double y, double r, double start_angle, double arc_angle, int32_t color = 0xFFFF00);
	void line(double x1, double y1, double x2, double y2, int32_t color = 0x0000FF);
	void text(double x, double y, const char* text, int32_t color = 0x000000);
	
private:
	void sendCommand(const char* str);
	void writeWithColor(char* buf, int32_t color);

	int sfd;
};

#endif
