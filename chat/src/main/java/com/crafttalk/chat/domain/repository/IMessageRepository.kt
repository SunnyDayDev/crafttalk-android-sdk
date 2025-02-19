package com.crafttalk.chat.domain.repository

import android.content.Context
import androidx.paging.DataSource
import com.crafttalk.chat.data.local.db.entity.MessageEntity
import com.crafttalk.chat.domain.entity.file.TypeDownloadProgress
import com.crafttalk.chat.domain.entity.message.NetworkMessage
import com.crafttalk.chat.domain.transfer.TransferFileInfo

interface IMessageRepository {

    fun getMessages(): DataSource.Factory<Int, MessageEntity>

    fun getCountUnreadMessages(currentReadMessageTime: Long): Int?

    fun getTimestampMessageById(messageId: String): Long?

    fun getCountMessagesInclusiveTimestamp(timestampMessage: Long): Int?

    fun getCountUnreadMessagesRange(currentReadMessageTime: Long, timestampLastMessage: Long): Int?

    // получение времени первого сообщения
    suspend fun getTimeFirstMessage(): Long?

    // получение времени последнего сообщения
    suspend fun getTimeLastMessage(): Long?

    // загрузка определенного пула сообщений
    suspend fun uploadMessages(
        uuid: String,
        startTime: Long?,
        endTime: Long,
        updateReadPoint: (newTimeMark: Long) -> Boolean,
        syncMessagesAcrossDevices: (countUnreadMessages: Int) -> Unit,
        returnedEmptyPool: () -> Unit,
        getPersonPreview: suspend (personId: String) -> String?,
        getFileInfo: suspend (context: Context, networkMessage: NetworkMessage) -> TransferFileInfo?
    ): List<MessageEntity>

    suspend fun mergeNewMessages()

    suspend fun updatePersonNames(
        messages: List<MessageEntity>,
        updatePersonName: suspend (personId: String?, currentPersonName: String?) -> Unit
    )

    suspend fun getFileInfo(
        context: Context,
        networkMessage: NetworkMessage
    ): TransferFileInfo?

    suspend fun sendMessages(message: String, repliedMessageId: String?)
    suspend fun selectAction(messageId: String, actionId: String)

    fun updateSizeMessage(id: String, height: Int, width: Int)

    fun updateTypeDownloadProgressOfMessageWithAttachment(id: String, typeDownloadProgress: TypeDownloadProgress)

    fun removeAllInfoMessages()

}