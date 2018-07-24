package org.activiti.runtime.api.model.payloads;

public class GetTaskVariablesPayload {

    private String taskId;
    private boolean localOnly;

    public GetTaskVariablesPayload() {
    }

    public GetTaskVariablesPayload(String taskId,
                                   boolean localOnly) {
        this.taskId = taskId;
        this.localOnly = localOnly;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isLocalOnly() {
        return localOnly;
    }

    public void setLocalOnly(boolean localOnly) {
        this.localOnly = localOnly;
    }
}
