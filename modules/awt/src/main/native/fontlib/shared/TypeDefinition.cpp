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


#include "TypeDefinition.h"

fint fwcslen(fwchar_t* str)
{
    if (!str)
        return 0;
        
	fint counter=0;
	fwchar_t* tmpstr = str;
	while(*tmpstr != 0)
	{
		tmpstr++;
		counter++;
	}
	return counter;
}

fint fwcscmp(fwchar_t* str1, fwchar_t* str2)
{
    if (str1 == 0 || str2 == 0)
        return str1 - str2;
        
	fwchar_t* tmpstr1 = str1; 
	fwchar_t* tmpstr2 = str2;
	while(*tmpstr1 != 0 || *tmpstr2 !=0)
	{
        if (*tmpstr1 != *tmpstr2)
			return -1;

		tmpstr1++;
		tmpstr2++;
	}

	return 0;
}