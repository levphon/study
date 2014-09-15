package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoServer {

	public final static int PORT = 8080;

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new EchoServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Bind and start to accept incoming connections.
			ChannelFuture future = bootstrap.bind(PORT).sync();
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}

/**
 * Handles a server-side channel.
 */
class EchoServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf input = (ByteBuf) msg;
		System.out.println("EchoServer:" + input.toString(CharsetUtil.UTF_8));

		ByteBuf output = Unpooled.buffer().writeBytes("hello->".getBytes()).writeBytes(input);
//		ctx.writeAndFlush(output);
		ctx.write(output);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

}
