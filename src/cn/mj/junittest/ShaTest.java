package cn.mj.junittest;

import org.junit.Test;

import cn.itcast.shop.utils.SHAUtil;

public class ShaTest {
	@Test
	public void test01() throws Exception{
		String str = "123123";
		String sha = SHAUtil.shaEncode(str);
		System.out.println(str);
		System.out.println(sha);
	}

}
