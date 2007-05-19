/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
* Zip Support Header
*/

#if !defined(ZIPSUP_H)
#define ZIPSUP_H
#if defined(__cplusplus)
extern "C"
{
#endif
#include "hyport.h"
#include "hyzip.h"

#define HY_ZIP_DLL_NAME "hyzlib"

  typedef struct HyZipCache
  {
    U_8 *zipFileName;
    IDATA zipFileSize;
    I_64 zipTimeStamp;
    IDATA startCentralDir;
    struct HyPortLibrary *portLib;
    void *cachePool;
    void *cachePoolEntry;
  } HyZipCache;


  typedef struct HyZipCentralEnd
  {
    U_16 diskNumber;
    U_16 dirStartDisk;
    U_16 thisDiskEntries;
    U_16 totalEntries;
    U_32 dirSize;
    U_32 dirOffset;
    U_16 commentLength;
    char _hypadding0012[2];   /* 2 bytes of automatic padding */
    U_8 *comment;
  } HyZipCentralEnd;


  typedef struct HyZipDataDescriptor
  {
    U_32 crc32;
    U_32 compressedSize;
    U_32 uncompressedSize;
  } HyZipDataDescriptor;


/* HySourceZipSupport*/
  extern HY_CFUNC I_32 zip_getZipEntryData
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
  extern HY_CFUNC I_32 zip_getZipEntryFromOffset
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, IDATA offset));
  extern HY_CFUNC I_32 zip_establishCache
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile));
  extern HY_CFUNC void zip_resetZipFile
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                IDATA * nextEntryPointer));
  extern HY_CFUNC I_32 zip_getNextZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * zipEntry, IDATA * nextEntryPointer));
  extern HY_CFUNC I_32 zip_getZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, const char *filename,
                BOOLEAN findDirectory));
  extern HY_CFUNC I_32 zip_getZipEntryExtraField
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
  extern HY_CFUNC void zip_initZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
  extern HY_CFUNC I_32 zip_openZipFile
    PROTOTYPE ((HyPortLibrary * portLib, char *filename, HyZipFile * zipFile,
                HyZipCachePool * cachePool));
  extern HY_CFUNC void zip_freeZipEntry
    PROTOTYPE ((HyPortLibrary * portLib, HyZipEntry * entry));
  struct HyZipFile;
  extern HY_CFUNC I_32 VMCALL zip_closeZipFile
    PROTOTYPE ((HyPortLibrary * portLib, struct HyZipFile * zipFile));
  extern HY_CFUNC I_32 zip_getZipEntryComment
    PROTOTYPE ((HyPortLibrary * portLib, HyZipFile * zipFile,
                HyZipEntry * entry, U_8 * buffer, U_32 bufferSize));
/* HySourceZipCache*/
  extern HY_CFUNC UDATA zipCache_findElement
    PROTOTYPE ((HyZipCache * zipCache, const char *elementName,
                BOOLEAN searchDirList));
  extern HY_CFUNC void zipCache_kill PROTOTYPE ((HyZipCache * zipCache));
  extern HY_CFUNC IDATA zipCache_enumGetDirName
    PROTOTYPE ((void *handle, char *nameBuf, UDATA nameBufSize));
  extern HY_CFUNC HyZipCache *zipCache_new
    PROTOTYPE ((HyPortLibrary * portLib, char *zipName, IDATA zipNameLength));
  extern HY_CFUNC IDATA zipCache_enumNew
    PROTOTYPE ((HyZipCache * zipCache, char *directoryName, void **handle));
  extern HY_CFUNC IDATA zipCache_enumElement
    PROTOTYPE ((void *handle, char *nameBuf, UDATA nameBufSize,
                UDATA * offset));
  extern HY_CFUNC void zipCache_enumKill PROTOTYPE ((void *handle));
  extern HY_CFUNC BOOLEAN zipCache_addElement
    PROTOTYPE ((HyZipCache * zipCache, char *elementName,
                UDATA elementOffset));
/* HySourceZipCachePool*/
  extern HY_CFUNC BOOLEAN zipCachePool_release
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
  extern HY_CFUNC void zipCachePool_kill PROTOTYPE ((HyZipCachePool * zcp));
  extern HY_CFUNC HyZipCache *zipCachePool_findCache
    PROTOTYPE ((HyZipCachePool * zcp, char const *zipFileName,
                IDATA zipFileNameLength, IDATA zipFileSize,
                I_64 zipTimeStamp));
  extern HY_CFUNC HyZipCachePool *zipCachePool_new
    PROTOTYPE ((HyPortLibrary * portLib));
  extern HY_CFUNC BOOLEAN zipCachePool_addCache
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
  extern HY_CFUNC BOOLEAN zipCachePool_addRef
    PROTOTYPE ((HyZipCachePool * zcp, HyZipCache * zipCache));
#if defined(__cplusplus)
}
#endif
#endif /* ZIPSUP_H */
