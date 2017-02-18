BEGIN TRANSACTION

BEGIN TRY

DELETE FROM RT_FIELD_VALUES WHERE REF_TABLE_FIELD_ID IN (SELECT REF_TABLE_FIELD_ID FROM RT_TABLE_FIELD WHERE REF_TABLE_ID IN (SELECT REF_TABLE_ID FROM RT_TABLE WHERE NAME = 'SSISPANINFOVRF' ))
DELETE FROM RT_TABLE_FIELD WHERE REF_TABLE_ID IN (SELECT REF_TABLE_ID FROM RT_TABLE WHERE NAME = 'SSISPANINFOVRF' )
DELETE FROM RT_TABLE WHERE NAME = 'SSISPANINFOVRF'
DECLARE @ref_table_id bigint 
 

,@ref_table_field_id_1 bigint
,@ref_table_field_id_2 bigint
,@ref_table_field_id_3 bigint
,@ref_table_field_id_4 bigint

,@ref_field_row_id_1 bigint
,@ref_field_row_id_2 bigint
,@ref_field_row_id_3 bigint
,@ref_field_row_id_4 bigint

set @ref_table_id = NEXT VALUE FOR RT_TABLE_1SQ

set @ref_table_field_id_1 = NEXT VALUE FOR RT_TABLE_FIELD_1SQ
set @ref_table_field_id_2 = NEXT VALUE FOR RT_TABLE_FIELD_1SQ
set @ref_table_field_id_3 = NEXT VALUE FOR RT_TABLE_FIELD_1SQ
set @ref_table_field_id_4 = NEXT VALUE FOR RT_TABLE_FIELD_1SQ

set @ref_field_row_id_1  = NEXT VALUE FOR RT_FIELD_VALUES_1SQ
set @ref_field_row_id_2  = NEXT VALUE FOR RT_FIELD_VALUES_1SQ
set @ref_field_row_id_3  = NEXT VALUE FOR RT_FIELD_VALUES_1SQ
set @ref_field_row_id_4  = NEXT VALUE FOR RT_FIELD_VALUES_1SQ

INSERT INTO [dbo].[RT_TABLE]([REF_TABLE_ID],[TABLE_ID],[VERSION],[NAME],[DESCRIPTION],[COMMENTS],[STATUS],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[EFF_BEGIN_DT],[EFF_END_DT],[ARCHIVE_DT]) VALUES (@ref_table_id ,NEXT VALUE FOR RT_TABLE_3SQ,1,'SSISPANINFOVRF','','','A','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_0SQ,GETDATE(),NULL,'2999-12-31 00:00:00')

INSERT INTO [dbo].[RT_TABLE_FIELD]([REF_TABLE_FIELD_ID],[REF_TABLE_ID],[FIELD_ID],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_1,@ref_table_id  ,2,'Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_FIELD_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_TABLE_FIELD]([REF_TABLE_FIELD_ID],[REF_TABLE_ID],[FIELD_ID],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_2,@ref_table_id  ,3,'Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_FIELD_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_TABLE_FIELD]([REF_TABLE_FIELD_ID],[REF_TABLE_ID],[FIELD_ID],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_3,@ref_table_id  ,6,'Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_FIELD_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_TABLE_FIELD]([REF_TABLE_FIELD_ID],[REF_TABLE_ID],[FIELD_ID],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_4,@ref_table_id  ,50025614,'Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_FIELD_0SQ,'2999-12-31 00:00:00')


INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_1 ,@ref_field_row_id_1 ,'CS','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_2 ,@ref_field_row_id_1 ,'Client Statement','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_3 ,@ref_field_row_id_1 ,'N','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_4 ,@ref_field_row_id_1 ,'N','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_1 ,@ref_field_row_id_2 ,'HC','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_2 ,@ref_field_row_id_2 ,'Hard Copy','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_3 ,@ref_field_row_id_2 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_4 ,@ref_field_row_id_2 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_1 ,@ref_field_row_id_3 ,'IN','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_2 ,@ref_field_row_id_3 ,'Interface','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_3 ,@ref_field_row_id_3 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_4 ,@ref_field_row_id_3 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_1 ,@ref_field_row_id_4 ,'TP','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_2 ,@ref_field_row_id_4 ,'Third Party Phone Contact','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_3 ,@ref_field_row_id_4 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')
INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_4 ,@ref_field_row_id_4 ,'Y','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')


END TRY

BEGIN CATCH

select ERROR_MESSAGE() AS ERRORMESSAGE, ERROR_LINE() AS ErrorLine
IF @@TRANCOUNT > 0  ROLLBACK TRANSACTION

END CATCH

IF @@TRANCOUNT >0  COMMIT TRANSACTION
