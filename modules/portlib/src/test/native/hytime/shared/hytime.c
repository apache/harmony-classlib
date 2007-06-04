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
#include <stdio.h>

#include "hycomp.h"
#include "hyport.h"
#include "hythread.h"

int main (int argc, char **argv, char **envp)
{
  HyPortLibrary hyportLibrary;
  HyPortLibraryVersion portLibraryVersion;
#ifdef HY_NO_THR
  HyThreadLibrary *privateThreadLibrary;
#endif
  UDATA msec, usec;
  I_64 millis;
  U_64 hires, hires2, freq, delta;

  printf("hytime:\n");

  HYPORT_SET_VERSION (&portLibraryVersion, HYPORT_CAPABILITY_MASK);
  if (0 != hyport_init_library (&hyportLibrary, &portLibraryVersion,
                                sizeof (HyPortLibrary)))
  {
    fprintf(stderr, "portlib init failed\n");
    return 1;
  }

  printf("  portlib initialized\n");

#ifdef HY_NO_THR
  privateThreadLibrary = hyportLibrary.port_get_thread_library(&hyportLibrary);
#endif

  msec = hyportLibrary.time_msec_clock(&hyportLibrary);
  printf("msec = %u\n", msec);

  usec = hyportLibrary.time_usec_clock(&hyportLibrary);
  printf("usec = %u\n", usec);

  millis = hyportLibrary.time_current_time_millis(&hyportLibrary);
  printf("millis = %lld\n", millis);

  hires = hyportLibrary.time_hires_clock(&hyportLibrary);
  printf("hires = %llu\n", hires);
  freq = hyportLibrary.time_hires_frequency(&hyportLibrary);
  printf("freq = %llu\n", freq);

  hythread_sleep(1000);

  hires2 = hyportLibrary.time_hires_clock(&hyportLibrary);
  printf("hires2 = %llu\n", hires2);

  delta = hyportLibrary.time_hires_delta(&hyportLibrary,
                                         hires, hires2,
                                         HYPORT_TIME_DELTA_IN_MICROSECONDS);
  printf("delta = %llu\n", delta);
  
  if (delta <= 0) {
    fprintf(stderr, "hires_clock did not increment after 1s sleep\n");
    return 1;
  }
  
  if (0 != hyportLibrary.port_shutdown_library (&hyportLibrary)) {
    fprintf(stderr, "portlib shutdown failed\n");
    return 1;
  }
  printf("  portlib shutdown\n");

  return 0;
}
