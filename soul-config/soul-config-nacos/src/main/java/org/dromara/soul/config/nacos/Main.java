/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dromara.soul.config.nacos;

import org.dromara.soul.common.utils.StringUtils;
import org.dromara.soul.config.api.ConfigEnv;
import org.dromara.soul.config.api.ConfigLoader;
import org.dromara.soul.config.api.original.Server;
import org.dromara.soul.config.api.original.ServerConfigLoader;
import org.dromara.soul.config.api.original.SoulDataBase;
import org.dromara.soul.config.api.original.SoulRegister;

/**
 * Main .
 * <p>
 * <p>
 * 2019-08-12 20:49
 *
 * @author chenbin sixh
 */
public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        ServerConfigLoader loader = new ServerConfigLoader();
        loader.load(ConfigLoader.Context::new, (context, config) -> {
            System.out.println("config:---->" + config);
            if (config != null) {
                Server server = config;
                if (StringUtils.isNotBlank(server.getConfigMode())) {
                    String configMode = server.getConfigMode();
                    if (configMode.equals("nacos")) {
                        new NacosConfigLoader().load(context, (context1, config1) -> System.out.println("config1:-->" + config1));
                    }
                }
            }
        });
        Server config = ConfigEnv.getInstance().getConfig(Server.class);
        SoulDataBase dataBase = ConfigEnv.getInstance().getConfig(SoulDataBase.class);
        SoulRegister register = ConfigEnv.getInstance().getConfig(SoulRegister.class);
        System.out.println(config);
        System.out.println(dataBase);
        System.out.println(register);
    }
}
