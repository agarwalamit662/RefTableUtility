package com.refTable.mukesh;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FwPropertyMapper{

	public JSONObject pmJSON;
	public JSONObject auJSON;
	public JSONObject baMapperJSON;
	public static Map<String,Map<String,ArrayList<String>>> innerMap = new HashMap<>();
	
	String abc = "{\"Amit\":\"Hello\"}";	
	
	public FwPropertyMapper() throws JSONException, IOException{
	
		pmJSON = new JSONObject("{\"mappers\":{\"property\":[{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchCatExceptionsCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"recordNum\",\"to\":\"RECORD_NUM\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}},{\"keysprop\":{\"from\":\"exceptionId\",\"to\":\"EXCEPTION_ID\"}},{\"keysprop\":{\"from\":\"jobcontext\",\"to\":\"JOBCONTEXT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchContactCargo\"},\"map\":[{\"keysprop\":{\"from\":\"contactId\",\"to\":\"CONTACT_ID\"}},{\"keysprop\":{\"from\":\"tpId\",\"to\":\"TP_ID\"}},{\"keysprop\":{\"from\":\"secEmail\",\"to\":\"SEC_EMAIL\"}},{\"keysprop\":{\"from\":\"homePhNum\",\"to\":\"HOME_PH_NUM\"}},{\"keysprop\":{\"from\":\"nameFirst\",\"to\":\"NAME_FIRST\"}},{\"keysprop\":{\"from\":\"nameLast\",\"to\":\"NAME_LAST\"}},{\"keysprop\":{\"from\":\"comments\",\"to\":\"COMMENTS\"}},{\"keysprop\":{\"from\":\"busPhNum\",\"to\":\"BUS_PH_NUM\"}},{\"keysprop\":{\"from\":\"cellPhNum\",\"to\":\"CELL_PH_NUM\"}},{\"keysprop\":{\"from\":\"priEmail\",\"to\":\"PRI_EMAIL\"}},{\"keysprop\":{\"from\":\"faxPhNum\",\"to\":\"FAX_PH_NUM\"}},{\"keysprop\":{\"from\":\"pagerPhNum\",\"to\":\"PAGER_PH_NUM\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchCpcFileStatusCargo\"},\"map\":[{\"keysprop\":{\"from\":\"fileId\",\"to\":\"FILE_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"fileReceivedDt\",\"to\":\"FILE_RECEIVED_DT\"}},{\"keysprop\":{\"from\":\"filePrintDt\",\"to\":\"FILE_PRINT_DT\"}},{\"keysprop\":{\"from\":\"fileMailedDt\",\"to\":\"FILE_MAILED_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchExceptionsCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"recordNum\",\"to\":\"RECORD_NUM\"}},{\"keysprop\":{\"from\":\"programName\",\"to\":\"PROGRAM_NAME\"}},{\"keysprop\":{\"from\":\"exceptionTimeDt\",\"to\":\"EXCEPTION_TIME_DT\"}},{\"keysprop\":{\"from\":\"exceptionCd\",\"to\":\"EXCEPTION_CD\"}},{\"keysprop\":{\"from\":\"exceptionSummary\",\"to\":\"EXCEPTION_SUMMARY\"}},{\"keysprop\":{\"from\":\"exceptionDetail\",\"to\":\"EXCEPTION_DETAIL\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"columnType\",\"to\":\"COLUMN_TYPE\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"businessExceptionFlag\",\"to\":\"BUSINESS_EXCEPTION_FLAG\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}},{\"keysprop\":{\"from\":\"reportRunId\",\"to\":\"REPORT_RUN_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFileControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"logicalFileName\",\"to\":\"LOGICAL_FILE_NAME\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"fileCreationDt\",\"to\":\"FILE_CREATION_DT\"}},{\"keysprop\":{\"from\":\"recordCount\",\"to\":\"RECORD_COUNT\"}},{\"keysprop\":{\"from\":\"discrepancyQty\",\"to\":\"DISCREPANCY_QTY\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"statusCd\",\"to\":\"STATUS_CD\"}},{\"keysprop\":{\"from\":\"fileId\",\"to\":\"FILE_ID\"}},{\"keysprop\":{\"from\":\"dependentJobStatus\",\"to\":\"DEPENDENT_JOB_STATUS\"}},{\"keysprop\":{\"from\":\"parameterValue\",\"to\":\"PARAMETER_VALUE\"}},{\"keysprop\":{\"from\":\"metaData\",\"to\":\"META_DATA\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFtpFileControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"environment\",\"to\":\"ENVIRONMENT\"}},{\"keysprop\":{\"from\":\"ftpIpAddress\",\"to\":\"FTP_IP_ADDRESS\"}},{\"keysprop\":{\"from\":\"ftpUserName\",\"to\":\"FTP_USER_NAME\"}},{\"keysprop\":{\"from\":\"ftpPassword\",\"to\":\"FTP_PASSWORD\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"fileCreateJobId\",\"to\":\"FILE_CREATE_JOB_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFtpRunControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"fileId\",\"to\":\"FILE_ID\"}},{\"keysprop\":{\"from\":\"fileStatusCd\",\"to\":\"FILE_STATUS_CD\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"targetFileName\",\"to\":\"TARGET_FILE_NAME\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchJobContactCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"contactId\",\"to\":\"CONTACT_ID\"}},{\"keysprop\":{\"from\":\"primaryInd\",\"to\":\"PRIMARY_IND\"}},{\"keysprop\":{\"from\":\"contactType\",\"to\":\"CONTACT_TYPE\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchJobMasterCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"description\",\"to\":\"DESCRIPTION\"}},{\"keysprop\":{\"from\":\"seqNum\",\"to\":\"SEQ_NUM\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchJobTradePartnerCargo\"},\"map\":[{\"keysprop\":{\"from\":\"tpId\",\"to\":\"TP_ID\"}},{\"keysprop\":{\"from\":\"comments\",\"to\":\"COMMENTS\"}},{\"keysprop\":{\"from\":\"orgName\",\"to\":\"ORG_NAME\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchPrlelRunCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"fwKey\",\"to\":\"FW_KEY\"}},{\"keysprop\":{\"from\":\"parallelRunId\",\"to\":\"PARALLEL_RUN_ID\"}},{\"keysprop\":{\"from\":\"value\",\"to\":\"VALUE\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"statusCd\",\"to\":\"STATUS_CD\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchPrlelRunControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"fwKey\",\"to\":\"FW_KEY\"}},{\"keysprop\":{\"from\":\"parallelRunId\",\"to\":\"PARALLEL_RUN_ID\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"dataValue\",\"to\":\"DATA_VALUE\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchPrlelRunKeysCargo\"},\"map\":[{\"keysprop\":{\"from\":\"processorJobId\",\"to\":\"PROCESSOR_JOB_ID\"}},{\"keysprop\":{\"from\":\"preprocessorJobId\",\"to\":\"PREPROCESSOR_JOB_ID\"}},{\"keysprop\":{\"from\":\"processorKey\",\"to\":\"PROCESSOR_KEY\"}},{\"keysprop\":{\"from\":\"preprocessorKey\",\"to\":\"PREPROCESSOR_KEY\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchRestartControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"fwKey\",\"to\":\"FW_KEY\"}},{\"keysprop\":{\"from\":\"parallelRunId\",\"to\":\"PARALLEL_RUN_ID\"}},{\"keysprop\":{\"from\":\"value\",\"to\":\"VALUE\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchSummaryCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"recordNum\",\"to\":\"RECORD_NUM\"}},{\"keysprop\":{\"from\":\"summary\",\"to\":\"SUMMARY\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"programName\",\"to\":\"PROGRAM_NAME\"}},{\"keysprop\":{\"from\":\"columnType\",\"to\":\"COLUMN_TYPE\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"reportRunId\",\"to\":\"REPORT_RUN_ID\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchTxnLogCargo\"},\"map\":[{\"keysprop\":{\"from\":\"txnLogId\",\"to\":\"TXN_LOG_ID\"}},{\"keysprop\":{\"from\":\"auLogDt\",\"to\":\"AU_LOG_DT\"}},{\"keysprop\":{\"from\":\"batchId\",\"to\":\"BATCH_ID\"}},{\"keysprop\":{\"from\":\"tableNames\",\"to\":\"TABLE_NAMES\"}},{\"keysprop\":{\"from\":\"modeCd\",\"to\":\"MODE_CD\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwErrorContextCargo\"},\"map\":[{\"keysprop\":{\"from\":\"referenceId\",\"to\":\"REFERENCE_ID\"}},{\"keysprop\":{\"from\":\"methodName\",\"to\":\"METHOD_NAME\"}},{\"keysprop\":{\"from\":\"actionId\",\"to\":\"ACTION_ID\"}},{\"keysprop\":{\"from\":\"serviceName\",\"to\":\"SERVICE_NAME\"}},{\"keysprop\":{\"from\":\"sessionId\",\"to\":\"SESSION_ID\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}},{\"keysprop\":{\"from\":\"threadId\",\"to\":\"THREAD_ID\"}},{\"keysprop\":{\"from\":\"environmentTypeInd\",\"to\":\"ENVIRONMENT_TYPE_IND\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"serverName\",\"to\":\"SERVER_NAME\"}},{\"keysprop\":{\"from\":\"referenceText\",\"to\":\"REFERENCE_TEXT\"}},{\"keysprop\":{\"from\":\"errorPageDisplayedSw\",\"to\":\"ERROR_PAGE_DISPLAYED_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwErrorLogCargo\"},\"map\":[{\"keysprop\":{\"from\":\"referenceId\",\"to\":\"REFERENCE_ID\"}},{\"keysprop\":{\"from\":\"exceptionNum\",\"to\":\"EXCEPTION_NUM\"}},{\"keysprop\":{\"from\":\"exceptionDt\",\"to\":\"EXCEPTION_DT\"}},{\"keysprop\":{\"from\":\"exceptionSeverity\",\"to\":\"EXCEPTION_SEVERITY\"}},{\"keysprop\":{\"from\":\"exceptionSummary\",\"to\":\"EXCEPTION_SUMMARY\"}},{\"keysprop\":{\"from\":\"exceptionDetail\",\"to\":\"EXCEPTION_DETAIL\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwErrorLogDetailsCargo\"},\"map\":[{\"keysprop\":{\"from\":\"referenceId\",\"to\":\"REFERENCE_ID\"}},{\"keysprop\":{\"from\":\"exceptionNum\",\"to\":\"EXCEPTION_NUM\"}},{\"keysprop\":{\"from\":\"contextNum\",\"to\":\"CONTEXT_NUM\"}},{\"keysprop\":{\"from\":\"contextType\",\"to\":\"CONTEXT_TYPE\"}},{\"keysprop\":{\"from\":\"contextId\",\"to\":\"CONTEXT_ID\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwOpconBatchJobMasterCargo\"},\"map\":[{\"keysprop\":{\"from\":\"seqNum\",\"to\":\"SEQ_NUM\"}},{\"keysprop\":{\"from\":\"frequency\",\"to\":\"FREQUENCY\"}},{\"keysprop\":{\"from\":\"opconJobId\",\"to\":\"OPCON_JOB_ID\"}},{\"keysprop\":{\"from\":\"skdName\",\"to\":\"SKD_NAME\"}},{\"keysprop\":{\"from\":\"primaryMachineName\",\"to\":\"PRIMARY_MACHINE_NAME\"}},{\"keysprop\":{\"from\":\"alternateMachineName\",\"to\":\"ALTERNATE_MACHINE_NAME\"}},{\"keysprop\":{\"from\":\"startimage\",\"to\":\"STARTIMAGE\"}},{\"keysprop\":{\"from\":\"parameters\",\"to\":\"PARAMETERS\"}},{\"keysprop\":{\"from\":\"jobDependency\",\"to\":\"JOB_DEPENDENCY\"}},{\"keysprop\":{\"from\":\"latestStartTime\",\"to\":\"LATEST_START_TIME\"}},{\"keysprop\":{\"from\":\"maximumRunTime\",\"to\":\"MAXIMUM_RUN_TIME\"}},{\"keysprop\":{\"from\":\"skdid\",\"to\":\"SKDID\"}},{\"keysprop\":{\"from\":\"thresholdDependency\",\"to\":\"THRESHOLD_DEPENDENCY\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"estimatedRuntime\",\"to\":\"ESTIMATED_RUNTIME\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwSessionManagerCargo\"},\"map\":[{\"keysprop\":{\"from\":\"userId\",\"to\":\"USER_ID\"}},{\"keysprop\":{\"from\":\"sesnId\",\"to\":\"SESN_ID\"}},{\"keysprop\":{\"from\":\"sesnStartDt\",\"to\":\"SESN_START_DT\"}},{\"keysprop\":{\"from\":\"sesnEndDt\",\"to\":\"SESN_END_DT\"}},{\"keysprop\":{\"from\":\"ipAddress\",\"to\":\"IP_ADDRESS\"}},{\"keysprop\":{\"from\":\"value\",\"to\":\"VALUE\"}},{\"keysprop\":{\"from\":\"empId\",\"to\":\"EMP_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"context\",\"to\":\"CONTEXT\"}},{\"keysprop\":{\"from\":\"envSw\",\"to\":\"ENV_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwUserCaseStatCargo\"},\"map\":[{\"keysprop\":{\"from\":\"typeCd\",\"to\":\"TYPE_CD\"}},{\"keysprop\":{\"from\":\"empId\",\"to\":\"EMP_ID\"}},{\"keysprop\":{\"from\":\"batchRunDt\",\"to\":\"BATCH_RUN_DT\"}},{\"keysprop\":{\"from\":\"locId\",\"to\":\"LOC_ID\"}},{\"keysprop\":{\"from\":\"typeCdValue\",\"to\":\"TYPE_CD_VALUE\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwUserFavoritesCargo\"},\"map\":[{\"keysprop\":{\"from\":\"userId\",\"to\":\"USER_ID\"}},{\"keysprop\":{\"from\":\"pageId\",\"to\":\"PAGE_ID\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchContextCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"fwKey\",\"to\":\"FW_KEY\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"contextEffectiveDt\",\"to\":\"CONTEXT_EFFECTIVE_DT\"}},{\"keysprop\":{\"from\":\"contextExpirationDt\",\"to\":\"CONTEXT_EXPIRATION_DT\"}},{\"keysprop\":{\"from\":\"jobKeyValue\",\"to\":\"JOB_KEY_VALUE\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFileCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"logicalFileName\",\"to\":\"LOGICAL_FILE_NAME\"}},{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"asOfDtEffectiveDt\",\"to\":\"AS_OF_DT_EFFECTIVE_DT\"}},{\"keysprop\":{\"from\":\"asOfDtExpirationDt\",\"to\":\"AS_OF_DT_EXPIRATION_DT\"}},{\"keysprop\":{\"from\":\"ftpIndicator\",\"to\":\"FTP_INDICATOR\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"changeCaseSw\",\"to\":\"CHANGE_CASE_SW\"}},{\"keysprop\":{\"from\":\"ftpBinarySw\",\"to\":\"FTP_BINARY_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFtpCommandCargo\"},\"map\":[{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"commandSequence\",\"to\":\"COMMAND_SEQUENCE\"}},{\"keysprop\":{\"from\":\"command\",\"to\":\"COMMAND\"}},{\"keysprop\":{\"from\":\"targetFileName\",\"to\":\"TARGET_FILE_NAME\"}},{\"keysprop\":{\"from\":\"agencyId\",\"to\":\"AGENCY_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchFtpFileLookupCargo\"},\"map\":[{\"keysprop\":{\"from\":\"fileName\",\"to\":\"FILE_NAME\"}},{\"keysprop\":{\"from\":\"agencyId\",\"to\":\"AGENCY_ID\"}},{\"keysprop\":{\"from\":\"ownerFa\",\"to\":\"OWNER_FA\"}},{\"keysprop\":{\"from\":\"targetFileName\",\"to\":\"TARGET_FILE_NAME\"}},{\"keysprop\":{\"from\":\"fileType\",\"to\":\"FILE_TYPE\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"jobStream\",\"to\":\"JOB_STREAM\"}},{\"keysprop\":{\"from\":\"ftpIpAddress\",\"to\":\"FTP_IP_ADDRESS\"}},{\"keysprop\":{\"from\":\"ftpType\",\"to\":\"FTP_TYPE\"}},{\"keysprop\":{\"from\":\"flagFileName\",\"to\":\"FLAG_FILE_NAME\"}},{\"keysprop\":{\"from\":\"ftpUserName\",\"to\":\"FTP_USER_NAME\"}},{\"keysprop\":{\"from\":\"ftpPassword\",\"to\":\"FTP_PASSWORD\"}},{\"keysprop\":{\"from\":\"intTag\",\"to\":\"INT_TAG\"}},{\"keysprop\":{\"from\":\"ftpAccount\",\"to\":\"FTP_ACCOUNT\"}},{\"keysprop\":{\"from\":\"targetLocation\",\"to\":\"TARGET_LOCATION\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchJobScheduleCargo\"},\"map\":[{\"keysprop\":{\"from\":\"eventId\",\"to\":\"EVENT_ID\"}},{\"keysprop\":{\"from\":\"jobStreamId\",\"to\":\"JOB_STREAM_ID\"}},{\"keysprop\":{\"from\":\"scheduleCommand\",\"to\":\"SCHEDULE_COMMAND\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"recurring\",\"to\":\"RECURRING\"}},{\"keysprop\":{\"from\":\"jobArguments\",\"to\":\"JOB_ARGUMENTS\"}},{\"keysprop\":{\"from\":\"eventType\",\"to\":\"EVENT_TYPE\"}},{\"keysprop\":{\"from\":\"jobSequenceNumber\",\"to\":\"JOB_SEQUENCE_NUMBER\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchParameterControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"parameters\",\"to\":\"PARAMETERS\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"parmEffectiveDt\",\"to\":\"PARM_EFFECTIVE_DT\"}},{\"keysprop\":{\"from\":\"parmExpirationDt\",\"to\":\"PARM_EXPIRATION_DT\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchRunControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"jobId\",\"to\":\"JOB_ID\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"startTimeDt\",\"to\":\"START_TIME_DT\"}},{\"keysprop\":{\"from\":\"endTimeDt\",\"to\":\"END_TIME_DT\"}},{\"keysprop\":{\"from\":\"statusCd\",\"to\":\"STATUS_CD\"}},{\"keysprop\":{\"from\":\"runNum\",\"to\":\"RUN_NUM\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"overWriteFlag\",\"to\":\"OVER_WRITE_FLAG\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"parallelRunId\",\"to\":\"PARALLEL_RUN_ID\"}},{\"keysprop\":{\"from\":\"restartFlag\",\"to\":\"RESTART_FLAG\"}},{\"keysprop\":{\"from\":\"parallelRunNum\",\"to\":\"PARALLEL_RUN_NUM\"}},{\"keysprop\":{\"from\":\"nodeId\",\"to\":\"NODE_ID\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchSortControlCargo\"},\"map\":[{\"keysprop\":{\"from\":\"outputLogicalFileName\",\"to\":\"OUTPUT_LOGICAL_FILE_NAME\"}},{\"keysprop\":{\"from\":\"inputLogicalFileNames\",\"to\":\"INPUT_LOGICAL_FILE_NAMES\"}},{\"keysprop\":{\"from\":\"sortKeys\",\"to\":\"SORT_KEYS\"}},{\"keysprop\":{\"from\":\"sortOptions\",\"to\":\"SORT_OPTIONS\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"fileStatusCd\",\"to\":\"FILE_STATUS_CD\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwDataElementListCargo\"},\"map\":[{\"keysprop\":{\"from\":\"deElementId\",\"to\":\"DE_ELEMENT_ID\"}},{\"keysprop\":{\"from\":\"deScreenElementName\",\"to\":\"DE_SCREEN_ELEMENT_NAME\"}},{\"keysprop\":{\"from\":\"deLabelText\",\"to\":\"DE_LABEL_TEXT\"}},{\"keysprop\":{\"from\":\"deDefaultValue\",\"to\":\"DE_DEFAULT_VALUE\"}},{\"keysprop\":{\"from\":\"langCd\",\"to\":\"LANG_CD\"}},{\"keysprop\":{\"from\":\"rdRefId\",\"to\":\"RD_REF_ID\"}},{\"keysprop\":{\"from\":\"deLastChanged\",\"to\":\"DE_LAST_CHANGED\"}},{\"keysprop\":{\"from\":\"deNotes\",\"to\":\"DE_NOTES\"}},{\"keysprop\":{\"from\":\"deAuthUser\",\"to\":\"DE_AUTH_USER\"}},{\"keysprop\":{\"from\":\"deMandatory\",\"to\":\"DE_MANDATORY\"}},{\"keysprop\":{\"from\":\"deWidth\",\"to\":\"DE_WIDTH\"}},{\"keysprop\":{\"from\":\"dePackage\",\"to\":\"DE_PACKAGE\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.FwPageCargo\"},\"map\":[{\"keysprop\":{\"from\":\"pageId\",\"to\":\"PAGE_ID\"}},{\"keysprop\":{\"from\":\"pageTitle\",\"to\":\"PAGE_TITLE\"}},{\"keysprop\":{\"from\":\"pageLongTitle\",\"to\":\"PAGE_LONG_TITLE\"}},{\"keysprop\":{\"from\":\"url\",\"to\":\"URL\"}},{\"keysprop\":{\"from\":\"sesnEjbNm\",\"to\":\"SESN_EJB_NM\"}},{\"keysprop\":{\"from\":\"helpTxt\",\"to\":\"HELP_TXT\"}},{\"keysprop\":{\"from\":\"busFnId\",\"to\":\"BUS_FN_ID\"}},{\"keysprop\":{\"from\":\"parentPageId\",\"to\":\"PARENT_PAGE_ID\"}},{\"keysprop\":{\"from\":\"pageNumber\",\"to\":\"PAGE_NUMBER\"}},{\"keysprop\":{\"from\":\"pageRequiredSw\",\"to\":\"PAGE_REQUIRED_SW\"}},{\"keysprop\":{\"from\":\"pageLoopSw\",\"to\":\"PAGE_LOOP_SW\"}},{\"keysprop\":{\"from\":\"leftNavigationSw\",\"to\":\"LEFT_NAVIGATION_SW\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"driverSw\",\"to\":\"DRIVER_SW\"}},{\"keysprop\":{\"from\":\"functionalAreaCd\",\"to\":\"FUNCTIONAL_AREA_CD\"}},{\"keysprop\":{\"from\":\"helpRequiredSw\",\"to\":\"HELP_REQUIRED_SW\"}},{\"keysprop\":{\"from\":\"questionSw\",\"to\":\"QUESTION_SW\"}},{\"keysprop\":{\"from\":\"summarySw\",\"to\":\"SUMMARY_SW\"}},{\"keysprop\":{\"from\":\"driverProcessCd\",\"to\":\"DRIVER_PROCESS_CD\"}},{\"keysprop\":{\"from\":\"helpCreateUserId\",\"to\":\"HELP_CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"helpUpdateUserId\",\"to\":\"HELP_UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"helpCreateDt\",\"to\":\"HELP_CREATE_DT\"}},{\"keysprop\":{\"from\":\"helpUpdateDt\",\"to\":\"HELP_UPDATE_DT\"}},{\"keysprop\":{\"from\":\"actualUrl\",\"to\":\"ACTUAL_URL\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"laptopSw\",\"to\":\"LAPTOP_SW\"}},{\"keysprop\":{\"from\":\"simulationSw\",\"to\":\"SIMULATION_SW\"}},{\"keysprop\":{\"from\":\"moduleSw\",\"to\":\"MODULE_SW\"}},{\"keysprop\":{\"from\":\"deprecatedSw\",\"to\":\"DEPRECATED_SW\"}},{\"keysprop\":{\"from\":\"caseLevelSecuritySw\",\"to\":\"CASE_LEVEL_SECURITY_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwPageActionsCargo\"},\"map\":[{\"keysprop\":{\"from\":\"pageId\",\"to\":\"PAGE_ID\"}},{\"keysprop\":{\"from\":\"methodId\",\"to\":\"METHOD_ID\"}},{\"keysprop\":{\"from\":\"methodName\",\"to\":\"METHOD_NAME\"}},{\"keysprop\":{\"from\":\"methodSignature\",\"to\":\"METHOD_SIGNATURE\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"slaType\",\"to\":\"SLA_TYPE\"}},{\"keysprop\":{\"from\":\"transactionSw\",\"to\":\"TRANSACTION_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwPageDataElementListCargo\"},\"map\":[{\"keysprop\":{\"from\":\"pageId\",\"to\":\"PAGE_ID\"}},{\"keysprop\":{\"from\":\"deElementId\",\"to\":\"DE_ELEMENT_ID\"}},{\"keysprop\":{\"from\":\"fieldHelp\",\"to\":\"FIELD_HELP\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"orphanSw\",\"to\":\"ORPHAN_SW\"}},{\"keysprop\":{\"from\":\"helpRequiredSw\",\"to\":\"HELP_REQUIRED_SW\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwParametersCargo\"},\"map\":[{\"keysprop\":{\"from\":\"parmId\",\"to\":\"PARM_ID\"}},{\"keysprop\":{\"from\":\"parmName\",\"to\":\"PARM_NAME\"}},{\"keysprop\":{\"from\":\"parmValue\",\"to\":\"PARM_VALUE\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwReleaseNotesCargo\"},\"map\":[{\"keysprop\":{\"from\":\"releaseNum\",\"to\":\"RELEASE_NUM\"}},{\"keysprop\":{\"from\":\"startDt\",\"to\":\"START_DT\"}},{\"keysprop\":{\"from\":\"expiryDt\",\"to\":\"EXPIRY_DT\"}},{\"keysprop\":{\"from\":\"notes\",\"to\":\"NOTES\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"historySeq\",\"to\":\"HISTORY_SEQ\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]},{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.cargo.custom.MuEdbcTriggerBatchCargo\"},\"map\":[{\"keysprop\":{\"from\":\"triggerSeqNum\",\"to\":\"TRIGGER_SEQ_NUM\"}},{\"keysprop\":{\"from\":\"asOfDt\",\"to\":\"AS_OF_DT\"}},{\"keysprop\":{\"from\":\"parallelRunId\",\"to\":\"PARALLEL_RUN_ID\"}},{\"keysprop\":{\"from\":\"executeSw\",\"to\":\"EXECUTE_SW\"}},{\"keysprop\":{\"from\":\"processedSw\",\"to\":\"PROCESSED_SW\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}}]}]}}");
		
		auJSON = new JSONObject("{\"mappers\":{\"property\":{\"keysprop\":{\"class\":\"gov.state.nextgen.framework.business.entities.FwReleaseNotesCargo\"},\"map\":[{\"keysprop\":{\"from\":\"releaseNum\",\"to\":\"RELEASE_NUM\"}},{\"keysprop\":{\"from\":\"startDt\",\"to\":\"START_DT\"}},{\"keysprop\":{\"from\":\"expiryDt\",\"to\":\"EXPIRY_DT\"}},{\"keysprop\":{\"from\":\"notes\",\"to\":\"NOTES\"}},{\"keysprop\":{\"from\":\"updateUserId\",\"to\":\"UPDATE_USER_ID\"}},{\"keysprop\":{\"from\":\"updateDt\",\"to\":\"UPDATE_DT\"}},{\"keysprop\":{\"from\":\"historySeq\",\"to\":\"HISTORY_SEQ\"}},{\"keysprop\":{\"from\":\"createUserId\",\"to\":\"CREATE_USER_ID\"}},{\"keysprop\":{\"from\":\"createDt\",\"to\":\"CREATE_DT\"}},{\"keysprop\":{\"from\":\"uniqueTransId\",\"to\":\"UNIQUE_TRANS_ID\"}},{\"keysprop\":{\"from\":\"archiveDt\",\"to\":\"ARCHIVE_DT\"}},{\"keysprop\":{\"from\":\"auditUserId\",\"to\":\"AUDIT_USER_ID\"}},{\"keysprop\":{\"from\":\"auditDt\",\"to\":\"AUDIT_DT\"}}]}}}");
		
		baMapperJSON = new JSONObject("{}");
		
		
		//insert into fast4j_table_columns(id_fast4j_table_columns, 
		//id_fast4j_tables, property_name, column_name, primary_key, create_dt, create_user_id, update_dt, update_user_id)
		//values(next value for id_fast4j_table_columns, @id_DcCaseCardholderDAO, 
		//'caseNum', 'CASE_NUM', 'Y', current_timestamp, 'Amit.Agrawal', current_timestamp, 'Amit.Agrawal')
		//insert into fast4j_table_columns(id_fast4j_table_columns, id_fast4j_tables, property_name, column_name, primary_key, create_dt, create_user_id, update_dt, update_user_id) values(next value for id_fast4j_table_columns, @id_DcCaseCardholderDAO, 'sequenceNum', 'SEQUENCE_NUM', 'Y', current_timestamp, 'Amit.Agrawal', current_timestamp, 'Amit.Agrawal')
		//insert into fast4j_table_columns(id_fast4j_table_columns, id_fast4j_tables, property_name, column_name, primary_key, create_dt, create_user_id, update_dt, update_user_id) values(next value for id_fast4j_table_columns, @id_DcCaseCardholderDAO, 'historySeq', 'HISTORY_SEQ', 'Y', current_timestamp, 'Amit.Agrawal', current_timestamp, 'Amit.Agrawal')

		
		JSONObject innerObj = pmJSON.getJSONObject("mappers");
		JSONArray arr = innerObj.getJSONArray("property");
		for(int i = 0; i < arr.length() ; i++){
		//for(int i = 0; i < arr.length() ; i++){
				JSONObject jobj = arr.getJSONObject(i);
				JSONObject jinobj = jobj.getJSONObject("keysprop");
				String classes = jinobj.getString("class");
				Map<String,ArrayList<String>> fromToMap = new HashMap<>();
				
				innerMap.put(classes, fromToMap);
				JSONArray mapArr = jobj.getJSONArray("map");
				for(int j = 0 ; j < mapArr.length(); j++){
					JSONObject kprop = mapArr.getJSONObject(j);
					JSONObject kpropsinner = kprop.getJSONObject("keysprop");
					
					ArrayList<String> arrList = new ArrayList<>(9);
					arrList.add("next value for fast4j_table_columns_0sq");
					arrList.add("@id_table_key");
					String from = kpropsinner.getString("from");
					arrList.add("'"+from+"'");
					String to = kpropsinner.getString("to");
					arrList.add("'"+to+"'");
					arrList.add("'N'");
					arrList.add("current_timestamp");
					arrList.add("'Amit.Agrawal'");
					arrList.add("current_timestamp");
					arrList.add("'Amit.Agrawal'");
					fromToMap.put(from, arrList);
					//System.out.println(from+" : "+to);
				}
				
		}
		
		//System.out.println(innerMap);
		
		JSONObject innerObj1 = auJSON.getJSONObject("mappers");
		JSONArray arr1 = innerObj.getJSONArray("property");
		for(int i = 0; i < arr1.length() ; i++){
		//for(int i = 0; i < arr.length() ; i++){
				JSONObject jobj1 = arr1.getJSONObject(i);
				JSONObject jinobj1 = jobj1.getJSONObject("keysprop");
				String classes1 = jinobj1.getString("class");
				if(TestUtility.keyValueWithCargoFW.containsKey(classes1)){
					
					TestUtility.keyValueWithCargoFW.get(classes1).set(4, "1");
				}
				
		}
		
		
		/*JSONObject innerObj2 = baMapperJSON.getJSONObject("mappers");
		JSONArray arr2 = innerObj2.getJSONArray("property");
		for(int i = 0; i < arr2.length() ; i++){
		//for(int i = 0; i < arr.length() ; i++){
				JSONObject jobj = arr2.getJSONObject(i);
				JSONObject jinobj = jobj.getJSONObject("keysprop");
				String classes = jinobj.getString("class");
				
				if(TestUtility.keyValueWithCargoFW.containsKey(classes)){
					
					TestUtility.keyValueWithCargoFW.get(classes).set(4, "2");
					JSONArray mapArr = jobj.getJSONArray("map");
					for(int j = 0 ; j < mapArr.length(); j++){
					JSONObject kprop = mapArr.getJSONObject(j);
					JSONObject kpropsinner = kprop.getJSONObject("keysprop");
					
					String from = kpropsinner.getString("from");
					
					String to = kpropsinner.getString("to");
					
					Map<String,ArrayList<String>> inrMap = innerMap.get(classes);
					inrMap.get(from).set(4, "'Y'");
					
					
				}
			}
		}*/
		int l = 0;
		
		StringBuffer buff = new StringBuffer();
		
		


		buff.append("DECLARE @id_table_key bigint\n ");
		
		for (Map.Entry<String, ArrayList<String>> entry : TestUtility.keyValueWithCargoFW.entrySet())
        {
            
			if(entry.getValue().size() > 1){
				
				/*buff.append("\n");
				buff.append("set @id_table_key = NEXT VALUE FOR fast4j_tables_0sq \n ");
				buff.append("\n");
				l++;
				buff.append("insert into fast4j_tables(id_fast4j_tables, function_name, fast4j_key, fast4j_value, table_type, active_sw, create_dt, create_user_id, update_dt, update_user_id) values( ");
				*/
				//System.out.println(entry.getKey() + "/" + entry.getValue());
            	
				
				StringBuffer buffInner = new StringBuffer();
            	if(innerMap.get(entry.getKey())!=null){
            	for (Map.Entry<String, ArrayList<String>> entryIn : innerMap.get(entry.getKey()).entrySet())
                {
                    if(entryIn.getValue().size() > 1){
                    	buffInner.append("insert into fast4j_table_columns(id_fast4j_table_columns, id_fast4j_tables, property_name, column_name, primary_key, create_dt, create_user_id, update_dt, update_user_id) values( ");
                    	//System.out.println(entryIn.getKey() + "/" + entryIn.getValue());
                    	
                    	for(int k = 0; k < entryIn.getValue().size(); k++){
    						if(k== (entryIn.getValue().size()-1)){
    							buffInner.append(entryIn.getValue().get(k)+" ) ");
    						}
    						else{
    							buffInner.append(entryIn.getValue().get(k)+" , ");
    						}
    					}
                    	buffInner.append("\n");
                    	
                	}
                    
                }
            		
            	buff.append("\n");
				buff.append("set @id_table_key = NEXT VALUE FOR fast4j_tables_0sq \n ");
				buff.append("\n");
				l++;
				buff.append("insert into fast4j_tables(id_fast4j_tables, function_name, fast4j_key, fast4j_value, table_type, active_sw, create_dt, create_user_id, update_dt, update_user_id) values( ");
            	
            	for(int k = 0; k < entry.getValue().size(); k++){
					if(k== (entry.getValue().size()-1)){
						buff.append(entry.getValue().get(k)+" ) ");
					}
					else{
						buff.append(entry.getValue().get(k)+" , ");
					}
				}
            	
            	buff.append("\n");
    			buff.append(buffInner.toString()+"\n");
            	
    			TestUtility.keyValue.remove(entry.getKey());
    			
            	}
            	/*else{
            		if(TestUtility.keyValueWithCargoFW.containsKey(entry.getKey())){
    					
    					TestUtility.keyValueWithCargoFW.get(entry.getKey()).set(5, "'N'");
    					
    					
    					
    				}
            	}*/
            	
            	/*for(int k = 0; k < entry.getValue().size(); k++){
					if(k== (entry.getValue().size()-1)){
						buff.append(entry.getValue().get(k)+" ) ");
					}
					else{
						buff.append(entry.getValue().get(k)+" , ");
					}
				}
            	
            	buff.append("\n");
    			buff.append(buffInner.toString()+"\n");*/
            	
            }
			
			
            /*if(l==30)
            	break;*/
        }
		
		
		String path = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/mukesh/";
		File f = new File(path+"fw"+".txt");
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeBytes(buff.toString());
		out.close();
		
		
	}
	
}
