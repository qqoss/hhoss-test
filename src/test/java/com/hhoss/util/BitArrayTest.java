package com.hhoss.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import com.hhoss.boot.App;
import com.hhoss.jour.Logger;

public class BitArrayTest {
	private static final Logger logger = Logger.get();
	
	@Test
	public void testTruncate(){
		BitArray ba1 = new BitArray(1<<16);//64k
		BitArray ba2 = genBitArray(1<<10);
		ba1.merge(ba2).truncate();
		assertNotEquals(ba1.dataWith(ba1.length()-1),0);
		assertEquals(ba1.bitCount(),ba2.bitCount());
	}
	
	@Test
	public void testRemove(){
		BitArray ba1 = genBitArray(256);
		BitArray ba2 = genBitArray(128);
		BitArray ba3 = genBitArray(128);
		BitArray ba4 = ba3.copy().remove(ba2);
		BitArray ba5 = ba2.copy().remove(ba3);
		assertEquals(ba1.dataSize(),4);
		assertEquals(ba4.copy().remove(ba2),ba4);
		assertEquals(ba5.dataSize(),ba4.dataSize());
		assertEquals(ba4.copy().merge(ba2),ba5.copy().merge(ba3));
		assertEquals(new BitArray(1<<16).merge(ba2).truncate(),ba2.copy().truncate());
		assertEquals(ba1.copy().remove(ba2).dataSize(),ba1.copy().remove(ba3).dataSize());
	}
	
	@Test
	public void testMerge(){
		BitArray ba1 = genBitArray(1<<16);
		BitArray ba2 = genBitArray(1<<12);
		BitArray ba3 = genBitArray(1<<10);
		assertEquals(ba1.copy().merge(ba2),ba2.copy().merge(ba1));
		assertEquals(ba1.copy().merge(ba2).dataSize(),ba1.dataSize());
		assertEquals(ba1.copy().merge(ba2).dataSize(),ba1.copy().merge(ba3).dataSize());
		assertEquals(new BitArray(1<<13).merge(ba2).truncate(),ba2.copy().truncate());
	}
	
	@Test	
	public void testSetClear(){
		int[] bits = {3,5,13,16,31,32,63};
		BitArray ba = new BitArray();
		for(int i:bits){ba.set(i);}	
		assertEquals(ba.toString0(0),"0001010000000100100000000000000110000000000000000000000000000001");
		
		ba.set(80);ba.set(127);
		assertEquals(ba.toString0(0),"00010100000001001000000000000001100000000000000000000000000000010000000000000000100000000000000000000000000000000000000000000001");
		ba.set(129);
		assertEquals(ba.toString0(0),"000101000000010010000000000000011000000000000000000000000000000100000000000000001000000000000000000000000000000000000000000000010100000000000000000000000000000000000000000000000000000000000000");
		ba.clear(129);ba.truncate();
		assertEquals(ba.toString0(0),"00010100000001001000000000000001100000000000000000000000000000010000000000000000100000000000000000000000000000000000000000000001");
		ba.clear(80);ba.clear(127);ba.truncate();
		assertEquals(ba.toString0(0),"0001010000000100100000000000000110000000000000000000000000000001");
		for(int i:bits){ba.clear(i);}ba.truncate();	
		assertEquals(ba.toString0(0),"");
	}
	
	@Test	
	public void testChangeBit(){
		int[] bits = {3,16,31,32,63,80,127};
		BitArray ba1 = new BitArray(192);
		for(int i:bits){ba1.set(i); }
		System.out.println(Arrays.toString(bits)+":"+ba1);
	}
	
	@Test	
	public void testStore(){
		BitArray ba = genBitArray(2048);
		File f = file("testBitArray01.bin");
		logger.info("file:{}",f);
		ba.store(f);
		assertTrue(f.exists());
		assertTrue(f.length()==2048/8);
	}
	
	private static BitArray genBitArray(int len){
		BitArray ba = new BitArray(len);
		for(int i=0;i<len;i++){
			ba.set(Random.get(len));
		}
		return ba;
	}
	
	private static File file(String s){
		return new File(App.getConfPath(),s);
	}
	
	public static void main(String[] args){
		BitArray ba = genBitArray(256);
		System.out.println(ba.toString0(4));
		System.out.println(ba.toString1(4));
		System.out.println(ba.toString2(4));
		System.out.println(ba.toString3(4));
		System.out.println(ba.toString4(4));
		//1,2,3相同,高位在前写出；0全反序,低位在前写出与1相反；4 低4位在前先输出与0每四位反序；
		
		
		BitArray ba2 = new BitArray(128);
		System.out.println(ba2.toString0(4));			
		for(int i=0;i<130;i++){
			ba2.set(i);
			System.out.println(ba2.toString4(4));			
		}

	}

}
