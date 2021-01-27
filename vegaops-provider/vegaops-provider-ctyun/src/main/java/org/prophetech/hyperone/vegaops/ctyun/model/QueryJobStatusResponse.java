package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prophetech.hyperone.vegaops.ctyun.model.CtyunResponse;

import java.util.List;

@Setter
@Getter
public class QueryJobStatusResponse extends CtyunResponse {
    /**
     * 	job id
     */
    private String job_id;
    /**
     * job的类型
     */
    private String job_type;
    /**
     * 	job的状态 SUCCESS：成功。 RUNNING：运行中。FAILED：失败。INIT：正在初始化。
     */
    private String status;
    /**
     * Job执行失败时的错误码
     */
    private String error_code;
    /**
     * Job执行失败时的错误原因
     */
    private String fail_reason;
    /**
     * Job操作的对象。根据不同Job类型，显示不同的内容，云服务器相关操作显示server_id，网卡相关操作显示nic_id。有子Job时为空
     */
    private Entities entities;

    private String begin_time;
    private String end_time;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Entities{
        /**
         * 子任务数量，没有子任务时为0
         */
        private String sub_jobs_total;
        /**
         * 每个子任务的执行信息。没有子任务时为空列表
         */
        private List<SubJobs> sub_jobs;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SubJobs{
        private String status;
        private String job_id;
        private String end_time;
        private String job_type;
        private String begin_time;
        private ServerId entities;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ServerId{
        private String server_id;
        private String nic_id;
    }
}
