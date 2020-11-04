package delimiter.codec;

import delimiter.serializable.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf o, List<Object> list) throws Exception {
        final int len = o.readableBytes();
        byte[] buf = new byte[len];
        o.getBytes(o.readerIndex(),buf,0,len);
        MessagePack messagePack = new MessagePack();
        final UserInfo read = messagePack.read(buf, UserInfo.class);
        list.add(read);
    }
}
