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

#include "zipsup.h"
#include "hyzip.h"
#include "vmi.h"
#include "hyport.h"

I_32 VMCALL 
hyzip_getZipEntryData(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * entry, U_8 * buffer, U_32 bufferSize) 
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getZipEntryData(PORTLIB, zipFile, entry, buffer, bufferSize);
}

I_32 VMCALL
hyzip_getZipEntryFromOffset(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * entry, IDATA offset) 
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getZipEntryFromOffset(PORTLIB, zipFile, entry, offset);
}

I_32 VMCALL 
hyzip_establishCache(VMInterface * vmi, struct HyZipFile * zipFile)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_establishCache(PORTLIB, zipFile);
}

void VMCALL
hyzip_resetZipFile(VMInterface * vmi, struct HyZipFile * zipFile, IDATA * nextEntryPointer)
{
	PORT_ACCESS_FROM_VMI(vmi);
	zip_resetZipFile(PORTLIB, zipFile, nextEntryPointer);
}

I_32 VMCALL 
hyzip_getNextZipEntry(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * zipEntry, IDATA * nextEntryPointer)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getNextZipEntry(PORTLIB, zipFile, zipEntry, nextEntryPointer);
}

I_32 VMCALL 
hyzip_getZipEntry(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * entry, const char *filename, BOOLEAN findDirectory)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getZipEntry(PORTLIB, zipFile,entry, filename, findDirectory);
}

I_32 VMCALL 
hyzip_getZipEntryExtraField(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * entry, U_8 * buffer, U_32 bufferSize)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getZipEntryExtraField(PORTLIB, zipFile, entry, buffer, bufferSize);
}

void VMCALL
hyzip_initZipEntry(VMInterface * vmi, struct HyZipEntry * entry)
{
	PORT_ACCESS_FROM_VMI(vmi);
	zip_initZipEntry(PORTLIB, entry);
}

I_32 VMCALL
hyzip_openZipFile(VMInterface * vmi, char *filename, struct HyZipFile * zipFile)
{
	PORT_ACCESS_FROM_VMI(vmi);
	
	HyZipFunctionTable *zipFuncs = (*vmi)->GetZipFunctions(vmi);
	/* This is a synchonization hole, should probably add a mutex to control setting this variable. */
	if ( zipFuncs->reserved == NULL ) {
		zipFuncs->reserved = zipCachePool_new(PORTLIB);
	}
	
	return zip_openZipFile(PORTLIB, filename, zipFile, (HyZipCachePool *)zipFuncs->reserved);
}

void VMCALL
hyzip_freeZipEntry(VMInterface * vmi, struct HyZipEntry * entry)
{
	PORT_ACCESS_FROM_VMI(vmi);
	zip_freeZipEntry(PORTLIB, entry);
}

I_32 VMCALL
hyzip_closeZipFile(VMInterface * vmi, struct HyZipFile * zipFile)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_closeZipFile(PORTLIB, zipFile);
}

I_32 VMCALL
hyzip_getZipEntryComment(VMInterface * vmi, struct HyZipFile * zipFile, struct HyZipEntry * entry, U_8 * buffer, U_32 bufferSize) 
{
	PORT_ACCESS_FROM_VMI(vmi);
	return zip_getZipEntryComment(PORTLIB, zipFile, entry, buffer, bufferSize);
}

UDATA VMCALL
hyzipCache_findElement(struct HyZipCache * zipCache, const char *elementName, BOOLEAN searchDirList)
{
	return zipCache_findElement(zipCache, elementName, searchDirList);
}

void VMCALL
hyzipCache_kill(struct HyZipCache * zipCache)
{
	zipCache_kill(zipCache);
}

IDATA VMCALL
hyzipCache_enumGetDirName(void *handle, char *nameBuf, UDATA nameBufSize)
{
	return zipCache_enumGetDirName(handle, nameBuf, nameBufSize);
}

struct HyZipCache *VMCALL 
hyzipCache_new(VMInterface * vmi, char *zipName, IDATA zipNameLength)
{
	PORT_ACCESS_FROM_VMI(vmi);
	return (struct HyZipCache *)zipCache_new(PORTLIB, zipName, zipNameLength);
}

IDATA VMCALL
hyzipCache_enumNew(struct HyZipCache * zipCache, char *directoryName, void **handle)
{
	return zipCache_enumNew(zipCache, directoryName, handle);
}
	
IDATA VMCALL 
hyzipCache_enumElement(void *handle, char *nameBuf, UDATA nameBufSize, UDATA * offset)
{
	return zipCache_enumElement(handle, nameBuf, nameBufSize, offset);
}

void VMCALL
hyzipCache_enumKill(void *handle)
{
	zipCache_enumKill(handle);
}

BOOLEAN VMCALL
hyzipCache_addElement(struct HyZipCache * zipCache, char *elementName, UDATA elementOffset)
{
	return zipCache_addElement(zipCache, elementName, elementOffset);
}

void * VMCALL
hyzip_zalloc(void *opaque, U_32 items, U_32 size)
{
	PORT_ACCESS_FROM_VMI (((VMInterface *) opaque));

	return hymem_allocate_memory (items * size);
}

void VMCALL 
hyzip_zfree(void *opaque, void *address)
{
	PORT_ACCESS_FROM_VMI ((VMInterface *) opaque);

	hymem_free_memory (address);

}

HyZipFunctionTable HyZipLibraryTable = {
	hyzip_getZipEntryData,
	hyzip_getZipEntryFromOffset,
	hyzip_establishCache,
	hyzip_resetZipFile,
	hyzip_getNextZipEntry,
	hyzip_getZipEntry,
	hyzip_getZipEntryExtraField,
	hyzip_initZipEntry,
	hyzip_openZipFile,
	hyzip_freeZipEntry,
	hyzip_closeZipFile,
	hyzip_getZipEntryComment,
	hyzipCache_findElement,
	hyzipCache_kill,
	hyzipCache_enumGetDirName,
	hyzipCache_new,
	hyzipCache_enumNew,
	hyzipCache_enumElement,
	hyzipCache_enumKill,
	hyzipCache_addElement,
	hyzip_zalloc,
	hyzip_zfree,
	NULL
};
