/**
 * *****************************************************************************
 * Copyright (c) 2011 Oracle. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 and Eclipse Distribution License v. 1.0 which accompanies
 * this distribution. The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution
 * License is available at http://www.eclipse.org/org/documents/edl-v10.php.
 * ****************************************************************************
 */
package org.glassfish.cdinosqldemo;

import com.mongodb.BasicDBObject;

/**
 * Customer, stored as a root JSON object.
 */
public class Customer extends BasicDBObject {

    public String getId() {
        return getString("_id");
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }
}