/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2019, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.clustering.infinispan.subsystem.remote;

import org.infinispan.client.hotrod.jmx.RemoteCacheClientStatisticsMXBean;
import org.jboss.as.clustering.controller.Operation;
import org.jboss.as.clustering.infinispan.subsystem.InfinispanExtension;
import org.jboss.as.controller.ExpressionResolver;
import org.jboss.as.controller.OperationDefinition;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.SimpleOperationDefinitionBuilder;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 * @author Paul Ferraro
 */
public enum RemoteCacheOperation implements Operation<RemoteCacheClientStatisticsMXBean> {

    RESET_STATISTICS("reset-statistics", ModelType.UNDEFINED) {
        @Override
        public ModelNode execute(ExpressionResolver resolver, ModelNode operation, RemoteCacheClientStatisticsMXBean statistics) throws OperationFailedException {
            statistics.resetStatistics();
            return null;
        }
    },
    ;
    private final OperationDefinition definition;

    RemoteCacheOperation(String name, ModelType replyType) {
        this.definition = new SimpleOperationDefinitionBuilder(name, InfinispanExtension.SUBSYSTEM_RESOLVER.createChildResolver(RemoteCacheResourceDefinition.WILDCARD_PATH))
                .setReplyType(replyType)
                .setRuntimeOnly()
                .build();
    }

    @Override
    public OperationDefinition getDefinition() {
        return this.definition;
    }
}