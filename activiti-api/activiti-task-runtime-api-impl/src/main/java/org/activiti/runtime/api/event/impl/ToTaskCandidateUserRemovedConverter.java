/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.runtime.api.event.impl;

import java.util.Optional;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.task.IdentityLink;
import org.activiti.runtime.api.event.TaskCandidateUserRemoved;
import org.activiti.runtime.api.model.impl.APITaskCandidateUserConverter;

import static org.activiti.engine.task.IdentityLinkType.CANDIDATE;

public class ToTaskCandidateUserRemovedConverter implements EventConverter<TaskCandidateUserRemoved, ActivitiEntityEvent> {

    private APITaskCandidateUserConverter converter;

    public ToTaskCandidateUserRemovedConverter(APITaskCandidateUserConverter converter) {
        this.converter = converter;
    }

    @Override
    public Optional<TaskCandidateUserRemoved> from(ActivitiEntityEvent internalEvent) {
        TaskCandidateUserRemoved event = null;
        if (internalEvent.getEntity() instanceof IdentityLink) {
            IdentityLink entity = (IdentityLink) internalEvent.getEntity();
            if (isCandidateUserEntity(entity)) {
                event = new TaskCandidateUserRemovedImpl(converter.from(entity));
            }
        }
        return Optional.ofNullable(event);
    }

    private boolean isCandidateUserEntity(IdentityLink identityLinkEntity) {
        return CANDIDATE.equalsIgnoreCase(identityLinkEntity.getType()) &&
                identityLinkEntity.getUserId() != null;
    }

}
