/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Victor A. Martynov
 * @version $Revision: 1.9.2.2 $
 */
package java.rmi.activation;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.Properties;


/**
 * @com.intel.drl.spec_ref
 *
 * @author  Victor A. Martynov
 * @version $Revision: 1.9.2.2 $
 */
public final class ActivationGroupDesc implements Serializable {

    private static final long serialVersionUID = -4936225423168276595L;

    /**
     * The group's fully package qualified class name.
     */
    String className;

    /**
     * The location from where to load the group's class.
     */
    String location;

    /**
     * The group's initialization data.
     */
    MarshalledObject data;

    /**
     * The controlling options for executing the VM in another process.
     */
    ActivationGroupDesc.CommandEnvironment env;

    /**
     * A properties map which will override those set by default in the subprocess environment
     */
    Properties props;

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupDesc(Properties props,
            ActivationGroupDesc.CommandEnvironment env) {
        this(null, null, null, props, env);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupDesc(String className, String codebase,
            MarshalledObject data, Properties props,
            ActivationGroupDesc.CommandEnvironment env) {

        this.className = className;
        this.location = codebase;
        this.data = data;
        this.props = props;
        this.env = env;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getClassName() {
        return className;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getLocation() {
        return location;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MarshalledObject getData() {
        return data;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ActivationGroupDesc.CommandEnvironment getCommandEnvironment() {
        return env;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Properties getPropertyOverrides() {
        if (props == null) {
            return null;
        } else {
            return (Properties) props.clone();
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (obj instanceof ActivationGroupDesc) {
            ActivationGroupDesc obj_agd = (ActivationGroupDesc) obj;
            boolean p0, p1, p2, p3, p4, p5;

            p0 = (location == null) ? obj_agd.location == null : location
                    .equals(obj_agd.location);
            p1 = (className == null) ? obj_agd.className == null : className
                    .equals(obj_agd.className);
            p2 = (data == null) ? obj_agd.data == null : data
                    .equals(obj_agd.data);
            p3 = (env == null) ? obj_agd.env == null : env.equals(obj_agd.env);
            p4 = (props == null) ? obj_agd.props == null : props
                    .equals(obj_agd.props);

            return p0 & p1 & p2 & p3 & p4;
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        int h0 = (className == null) ? 0 : className.hashCode();
        int h1 = (location == null) ? 0 : location.hashCode();
        int h2 = (data == null) ? 0 : data.hashCode();
        int h3 = (props == null) ? 0 : props.hashCode();

        return h0 ^ h1 ^ h2 ^ h3;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static class CommandEnvironment implements Serializable {

        private static final long serialVersionUID = 6165754737887770191L;

        private String command = null;

        private String options[] = null;

        /**
         * @com.intel.drl.spec_ref
         */
        public CommandEnvironment(String command, String[] options) {
            this.command = command;
            this.options = options;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public String[] getCommandOptions() {
            if (this.options == null) {
                return new String[0];
            }
            return this.options;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public String getCommandPath() {
            return this.command;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public boolean equals(Object obj) {
            if (obj instanceof CommandEnvironment) {
                CommandEnvironment casted_obj = (CommandEnvironment) obj;
                boolean p0 = casted_obj.getCommandPath().equals(command);
                boolean p1 = casted_obj.getCommandOptions().equals(options);
                return p0 && p1;
            }
            return false;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        public int hashCode() {
            int hc = 0;
            if (command != null) {
                hc = hc ^ command.hashCode();
            }
            if (options != null) {
                hc = hc ^ options.hashCode();
            }
            return hc;
        }
    }
}
