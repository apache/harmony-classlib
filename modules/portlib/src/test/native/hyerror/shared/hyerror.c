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
#include "hycomp.h"
#include "hyport.h"
#include <stdlib.h>
#include <string.h>

#include <stdio.h>
#define ERROR_STRING "Argh!"

int main (int argc, char **argv, char **envp)
{
  HyPortLibrary hyportLibrary;
  HyPortLibraryVersion portLibraryVersion;
  I_32 num;
  const char *err;

  printf("hyerror:\n");

  HYPORT_SET_VERSION (&portLibraryVersion, HYPORT_CAPABILITY_MASK);
  if (0 != hyport_init_library (&hyportLibrary, &portLibraryVersion,
                                sizeof (HyPortLibrary)))
  {
    fprintf(stderr, "portlib init failed\n");
    return 1;
  }

  printf("  portlib initialized\n");

  err = hyportLibrary.error_last_error_message(&hyportLibrary);
  printf("  err = %s\n", err);
  if (strncmp(err, "", sizeof("")) != 0) {
    fprintf(stderr,
            "hyerror_last_error_message was not empty but \"%s\"\n", err);
    return 1;
  }

  hyportLibrary.error_set_last_error(&hyportLibrary,
                                     2, HYPORT_ERROR_NOTFOUND);
  num = hyportLibrary.error_last_error_number(&hyportLibrary);
  err = hyportLibrary.error_last_error_message(&hyportLibrary);
  printf("  num = %d, err = %s\n", num, err);

  if (num != HYPORT_ERROR_NOTFOUND) {
    fprintf(stderr, "hyerror_last_error_number was %d not %d\n",
            num, HYPORT_ERROR_NOTFOUND);
    return 1;
  }

  if (strncmp(err, "", sizeof("")) == 0) {
    fprintf(stderr, "hyerror_last_error_message was empty\n");
    return 1;
  }

  hyportLibrary.error_set_last_error_with_message(&hyportLibrary,
                                                  HYPORT_ERROR_NOTFOUND,
                                                  ERROR_STRING);
  err = hyportLibrary.error_last_error_message(&hyportLibrary);
  printf("  err = %s\n", err);

  if (strncmp(err, ERROR_STRING, sizeof(ERROR_STRING)) != 0) {
    fprintf(stderr, "hyerror_last_error_message was %s not %s\n", err,
            ERROR_STRING);
    return 1;
  }
  
  if (0 != hyportLibrary.port_shutdown_library (&hyportLibrary)) {
    fprintf(stderr, "portlib shutdown failed\n");
    return 1;
  }
  printf("  portlib shutdown\n");

  return 0;
}
