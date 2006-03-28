/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "nethelp.h"
#include "jclglob.h"
#include "portsock.h"
#include "hyport.h"

//Alternative Select function
int
selectRead (JNIEnv * env,hysocket_t hysocketP, I_32 uSecTime, BOOLEAN accept){
  PORT_ACCESS_FROM_ENV (env);
  hytimeval_struct timeP;
  hyfdset_t fdset_read;
  I_32 result = 0;
  I_32 size = 0;
  if (0 <= uSecTime)
    hysock_timeval_init (0, uSecTime, &timeP);

  fdset_read = hymem_allocate_memory(sizeof (struct hyfdset_struct));
  FD_ZERO (&fdset_read->handle);
  FD_SET (hysocketP->sock, &fdset_read->handle);
  size =hysocketP->sock + 1;

  if (0 <= uSecTime)
    result = hysock_select (size, fdset_read, NULL, NULL,&timeP);
  else
    result = hysock_select (size, fdset_read, NULL, NULL,NULL);
  hymem_free_memory(fdset_read);
  return result;
}
