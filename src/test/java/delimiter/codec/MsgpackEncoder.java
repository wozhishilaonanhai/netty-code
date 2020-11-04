package delimiter.codec;

import delimiter.serializable.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

import java.io.IOException;

public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        final byte[] write = messagePack.write(o);
        byteBuf.writeBytes(write);
    }

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("hello");
        userInfo.setUserID(1);
        MessagePack messagePack = new MessagePack();
        final byte[] write = messagePack.write(userInfo);
        System.out.println(write.length);
        System.out.println(new String(write));
    }
}
