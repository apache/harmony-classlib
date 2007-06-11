/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Dmitriy S. Matveev, Viskov Nikolay 
 * @version $Revision$
 */
#ifndef __SHARED_OUTLINE_CLASS_H
#define __SHARED_OUTLINE_CLASS_H

#include <string>

/*typedef enum {
	SEG_CLOSE = 0,
	SEG_LINETO = 1, 
	SEG_MOVETO = 2, 
	SEG_CUBICTO = 3, 
	SEG_QUADTO = 4	
} SegmentType;*/

/*typedef enum SegmentTypeTag {
    SEG_MOVETO = 0, 	
	SEG_LINETO = 1, 
	SEG_QUADTO = 2,
	SEG_CUBICTO = 3, 		
    SEG_CLOSE = 4
} SegmentType;*/

static const unsigned char SEG_MOVETO = 0;
static const unsigned char SEG_LINETO = 1;
static const unsigned char SEG_QUADTO = 2;
static const unsigned char SEG_CUBICTO = 3;
static const unsigned char SEG_CLOSE = 4;

class Outline {
public:

	Outline(unsigned short pointsNumber, unsigned short commandNumber);

	~Outline();

	void lineTo(float x, float y);

	void moveTo(float x, float y);

	void quadTo(float x1, float y1, float x2, float y2);

	void curveTo(float x1, float y1, float x2, float y2, float x3, float y3);

	void closePath(void);

	unsigned short getPointsLength(void);
    unsigned short getCommandLength(void);

	void trim(void);

    float *_points;
	unsigned char *_commands;
	
private:
	unsigned short _pointsLength;	
    unsigned short _commandLenght;	

	unsigned short pointsCount;
	unsigned short commandsCount;
};

#endif //__SHARED_OUTLINE_CLASS_H
