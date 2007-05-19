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
#ifndef hyzip_h
#define hyzip_h

#include "hycomp.h"
#include "vmi.h"

#ifdef __cplusplus
extern "C" {
#endif
	
typedef struct HyZipCachePool HyZipCachePool;
	
#define ZIP_INTERNAL_MAX  80
#define ZIP_CM_Reduced1  2
#define ZIP_Unknown  0
#define ZIP_GZIP  2
#define ZIP_ERR_OUT_OF_MEMORY  -3
#define ZIP_ERR_FILE_CORRUPT  -6
#define ZIP_ERR_INTERNAL_ERROR  -11
#define ZIP_CM_Imploded  6
#define ZIP_CM_Reduced4  5
#define ZIP_CM_Shrunk  1
#define ZIP_CM_Reduced2  3
#define ZIP_ERR_FILE_READ_ERROR  -1
#define ZIP_CentralHeader  0x2014B50
#define ZIP_ERR_FILE_CLOSE_ERROR  -10
#define ZIP_ERR_BUFFER_TOO_SMALL  -7
#define ZIP_CM_Reduced3  4
#define ZIP_CM_Deflated  8
#define ZIP_LocalHeader  0x4034B50
#define ZIP_CM_Tokenized  7
#define ZIP_PKZIP  1
#define ZIP_CM_Stored  0
#define ZIP_ERR_UNSUPPORTED_FILE_TYPE  -5
#define ZIP_ERR_NO_MORE_ENTRIES  -2
#define ZIP_CentralEnd  0x6054B50
#define ZIP_ERR_FILE_OPEN_ERROR  -9
#define ZIP_ERR_UNKNOWN_FILE_TYPE  -4
#define ZIP_ERR_ENTRY_NOT_FOUND  -8
#define ZIP_DataDescriptor  0x8074B50

typedef struct HyZipEntry
{
  U_8 *data;
  U_8 *filename;
  U_8 *extraField;
  U_8 *fileComment;
  I_32 dataPointer;
  I_32 filenamePointer;
  I_32 extraFieldPointer;
  I_32 fileCommentPointer;
  U_32 compressedSize;
  U_32 uncompressedSize;
  U_32 crc32;
  U_16 filenameLength;
  U_16 extraFieldLength;
  U_16 fileCommentLength;
  U_16 internalAttributes;
  U_16 versionCreated;
  U_16 versionNeeded;
  U_16 flags;
  U_16 compressionMethod;
  U_16 lastModTime;
  U_16 lastModDate;
  U_8 internalFilename[80];
} HyZipEntry;

typedef struct HyZipFile
{
  U_8 *filename;
  void *cache;
  void *cachePool;
  I_32 fd;
  I_32 pointer;
  U_8 internalFilename[80];
  U_8 type;
  char _hypadding0065[3];  /* 3 bytes of automatic padding */
} HyZipFile;

typedef struct HyZipFunctionTable {
	I_32 (PVMCALL zip_getZipEntryData) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * entry, U_8 * buffer, U_32 bufferSize) ;
	I_32 (PVMCALL zip_getZipEntryFromOffset) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * entry, IDATA offset) ;
	I_32 (PVMCALL zip_establishCache) (VMInterface * vmi, HyZipFile * zipFile) ;
	void (PVMCALL zip_resetZipFile) (VMInterface * vmi, HyZipFile * zipFile, IDATA * nextEntryPointer) ;
	I_32 (PVMCALL zip_getNextZipEntry) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * zipEntry, IDATA * nextEntryPointer) ;
	I_32 (PVMCALL zip_getZipEntry) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * entry, const char *filename, BOOLEAN findDirectory) ;
	I_32 (PVMCALL zip_getZipEntryExtraField) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * entry, U_8 * buffer, U_32 bufferSize) ;
	void (PVMCALL zip_initZipEntry) (VMInterface * vmi, HyZipEntry * entry) ;
	I_32 (PVMCALL zip_openZipFile) (VMInterface * vmi, char *filename, HyZipFile * zipFile) ;
	void (PVMCALL zip_freeZipEntry) (VMInterface * vmi, HyZipEntry * entry) ;
	I_32 (PVMCALL zip_closeZipFile) (VMInterface * vmi, HyZipFile * zipFile) ;
	I_32 (PVMCALL zip_getZipEntryComment) (VMInterface * vmi, HyZipFile * zipFile, HyZipEntry * entry, U_8 * buffer, U_32 bufferSize) ;

  	UDATA (PVMCALL zipCache_findElement) (void * zipCache, const char *elementName, BOOLEAN searchDirList) ;
	void (PVMCALL zipCache_kill) (void * zipCache) ;
	IDATA (PVMCALL zipCache_enumGetDirName) (void *handle, char *nameBuf, UDATA nameBufSize) ;
	struct HyZipCache *(PVMCALL zipCache_new) (VMInterface * vmi, char *zipName, IDATA zipNameLength) ;
	IDATA (PVMCALL zipCache_enumNew) (void * zipCache, char *directoryName, void **handle) ;
	IDATA (PVMCALL zipCache_enumElement) (void *handle, char *nameBuf, UDATA nameBufSize, UDATA * offset) ;
	void (PVMCALL zipCache_enumKill) (void *handle) ;
	BOOLEAN (PVMCALL zipCache_addElement) (void * zipCache, char *elementName, UDATA elementOffset) ;
	
	void *(PVMCALL zip_zalloc) (void *opaque, U_32 items, U_32 size) ;
	void (PVMCALL zip_zfree) (void *opaque, void *address) ;
	
	void *reserved;
} HyZipFunctionTable;

#ifdef __cplusplus
}
#endif

#endif     /* hyzip_h */
