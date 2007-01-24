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
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "hycomp.h"
#include "hyport.h"

int main (int argc, char **argv, char **envp)
{
  HyPortLibrary hyportLibrary;
  HyPortLibraryVersion portLibraryVersion;
  IDATA fd;
  IDATA bytes;
  IDATA rc;
  I_64 offset;
  char buf[20];
  I_64 length;

  printf("hyfile:\n");

  HYPORT_SET_VERSION (&portLibraryVersion, HYPORT_CAPABILITY_MASK);
  if (0 != hyport_init_library (&hyportLibrary, &portLibraryVersion,
                                sizeof (HyPortLibrary)))
  {
    fprintf(stderr, "portlib init failed\n");
    return 1;
  }

  printf("  portlib initialized\n");

  fd = hyportLibrary.file_open(&hyportLibrary, "hytest.tmp",
                               HyOpenCreate | HyOpenWrite | HyOpenTruncate,
                               0600);
  printf("  fd = %d\n", fd);
  if (!fd) {
    fprintf(stderr, "Failed to open hytest.tmp for write %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  bytes = hyportLibrary.file_write(&hyportLibrary, fd, (void*)"testing", 7);
  printf("  hyfile_write wrote %d bytes\n", bytes);
  if (bytes != 7) {
    fprintf(stderr, "hyfile_write wrote %d bytes not %d\n", bytes, 7);
    exit(1);
  }

  rc = hyportLibrary.file_write_text(&hyportLibrary, fd, "testing", 7);
  if (rc != 0) {
    fprintf(stderr, "hyfile_write_text write failed: %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  hyportLibrary.file_printf(&hyportLibrary, fd, "%d%c%s\n", 1, '2', "3");
  rc = hyportLibrary.file_sync(&hyportLibrary, fd);
  if (rc != 0) {
    fprintf(stderr, "hyfile_sync failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  rc = hyportLibrary.file_close(&hyportLibrary, fd);
  if (rc != 0) {
    fprintf(stderr, "hyfile_close failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  rc = hyportLibrary.file_move(&hyportLibrary, "hytest.tmp", "hytest.tmp2");
  if (rc != 0) {
    fprintf(stderr, "hyfile_move failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  fd = hyportLibrary.file_open(&hyportLibrary, "hytest.tmp2",
                               HyOpenRead, 0000);
  printf("  fd = %d\n", fd);
  if (!fd) {
    fprintf(stderr, "Failed to open hytest.tmp2 for read %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  offset = hyportLibrary.file_seek(&hyportLibrary, fd, -11, HySeekEnd);
  printf("  offset = %d\n", offset);
  if (offset != 7) {
    fprintf(stderr, "Failed to seek hytest.tmp2\n");
    exit(1);
  }

  bytes = hyportLibrary.file_read(&hyportLibrary, fd, buf, 10);
  printf("  bytes = %d\n", bytes);
  buf[bytes] = '\0';
  printf("  buf = %s\n", buf);
  if (bytes != 10) {
    fprintf(stderr, "Failed to read hytest.tmp2\n");
    exit(1);
  }

  if (strcmp(buf, "testing123") != 0) {
    fprintf(stderr, "Failed to read correct content from hytest.tmp2\n");
    exit(1);
  }

  rc = hyportLibrary.file_close(&hyportLibrary, fd);
  if (rc != 0) {
    fprintf(stderr, "hyfile_close failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  length = hyportLibrary.file_length(&hyportLibrary, "hytest.tmp2");
  printf("  length = %d\n", length);
  if (length != 18) {
    fprintf(stderr, "hytest.tmp2 has incorrect length\n");
    exit(1);
  }

  if (hyportLibrary.file_attr(&hyportLibrary, "hytest.tmp2") != HyIsFile) {
    fprintf(stderr, "hytest.tmp2 has incorrect type\n");
    exit(1);
  }

  rc = hyportLibrary.file_unlink(&hyportLibrary, "hytest.tmp2");
  if (rc != 0) {
    fprintf(stderr, "hyfile_unlink failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  rc = hyportLibrary.file_mkdir(&hyportLibrary, "hytest.dir.tmp");
  if (rc != 0) {
    fprintf(stderr, "hyfile_mkdir failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  if (hyportLibrary.file_attr(&hyportLibrary, "hytest.dir.tmp") != HyIsDir) {
    fprintf(stderr, "hytest.dir.tmp has incorrect type\n");
    exit(1);
  }

  rc = hyportLibrary.file_unlinkdir(&hyportLibrary, "hytest.dir.tmp");
  if (rc != 0) {
    fprintf(stderr, "hyfile_unlinkdir failed %s\n",
            hyportLibrary.error_last_error_message(&hyportLibrary));
    exit(1);
  }

  if (0 != hyportLibrary.port_shutdown_library (&hyportLibrary)) {
    fprintf(stderr, "portlib shutdown failed\n");
    return 1;
  }
  printf("  portlib shutdown\n");

  return 0;
}
