package com.company.tictactoebootcamp.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.company.tictactoebootcamp.ScoreItems
import java.io.InputStream
import java.io.OutputStream

object ScoreSerializer: Serializer<ScoreItems> {
    override val defaultValue: ScoreItems = ScoreItems.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): ScoreItems {
        try {
            return ScoreItems.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ScoreItems,
        output: OutputStream
    ) = t.writeTo(output)
}