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

package org.wildfly.clustering.web.cache.session;

import org.wildfly.clustering.ee.Immutability;
import org.wildfly.clustering.marshalling.spi.Marshallability;
import org.wildfly.clustering.marshalling.spi.MarshalledValue;
import org.wildfly.clustering.marshalling.spi.MarshalledValueFactory;
import org.wildfly.clustering.marshalling.spi.MarshalledValueMarshaller;
import org.wildfly.clustering.marshalling.spi.Marshaller;
import org.wildfly.clustering.web.session.SessionManagerFactoryConfiguration;

/**
 * Configuration for a factory for creating {@link SessionAttributes} objects, based on marshalled values.
 * @author Paul Ferraro
 * @param <V> the attributes value type
 * @param <C> the marshalling context type
 * @param <L> the local context type
 */
public abstract class MarshalledValueSessionAttributesFactoryConfiguration<V, C extends Marshallability, L> implements SessionAttributesFactoryConfiguration<V, MarshalledValue<V, C>> {
    private final Immutability immutability;
    private final Marshaller<V, MarshalledValue<V, C>> marshaller;

    protected MarshalledValueSessionAttributesFactoryConfiguration(SessionManagerFactoryConfiguration<C, L> configuration) {
        MarshalledValueFactory<C> factory = configuration.getMarshalledValueFactory();
        C context = configuration.getMarshallingContext();
        this.immutability = configuration.getImmutability();
        this.marshaller = new MarshalledValueMarshaller<>(factory, context);
    }

    @Override
    public Marshaller<V, MarshalledValue<V, C>> getMarshaller() {
        return this.marshaller;
    }

    @Override
    public Immutability getImmutability() {
        return this.immutability;
    }
}