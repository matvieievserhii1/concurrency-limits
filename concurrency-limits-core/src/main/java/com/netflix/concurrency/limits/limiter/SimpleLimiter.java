/**
 * Copyright 2018 Netflix, Inc.
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
package com.netflix.concurrency.limits.limiter;

import java.util.Optional;

public class SimpleLimiter<ContextT> extends AbstractLimiter<ContextT> {
    public static class Builder<ContextT> extends AbstractLimiter.Builder<Builder<ContextT>, ContextT> {
        public SimpleLimiter build() {
            return new SimpleLimiter(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static <ContextT> Builder<ContextT> newBuilder() {
        return new Builder<ContextT>();
    }

    public SimpleLimiter(AbstractLimiter.Builder<?, ContextT> builder) {
        super(builder);
    }

    @Override
    public Optional<Listener> acquire(ContextT context) {
        if (getInflight() > getLimit()) {
            return Optional.empty();
        }
        return Optional.of(createListener());
    }
}
