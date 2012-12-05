package jp.co.mixi.rd.lucene.analysis;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import junit.framework.TestCase;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

/**
 * Simple tests to ensure the standard lucene factories are working.
 */
//public class StandardPlusTokenizerFactoryTest extends BaseTokenStreamTestCase {
public class StandardPlusTokenizerFactoryTest extends TestCase {

  @Test
  public void testStandardPlusTokenizer() throws Exception {
    try (Reader reader = new StringReader("Wha\u0301t's this thing do?")) {
      StandardPlusTokenizerFactory factory = new StandardPlusTokenizerFactory();
      factory.setLuceneMatchVersion(BaseTokenStreamTestCase.TEST_VERSION_CURRENT);
      Map<String, String> args = Collections.emptyMap();
      factory.init(args);
      try (Tokenizer stream = factory.create(reader)) {
        BaseTokenStreamTestCase.assertTokenStreamContents(stream,
            new String[] {"Wha\u0301t's", "this", "thing", "do", "?" });
      }
    }
  }

  @Test
  public void testStandardPlusTokenizerMaxTokenLength() throws Exception {
    StringBuilder builder = new StringBuilder();
    for (int i = 0 ; i < 100 ; ++i) {
      builder.append("abcdefg"); // 7 * 100 = 700 char "word"
    }
    String longWord = builder.toString();
    String content = "one two three " + longWord + " four five six";
    try (Reader reader = new StringReader(content)) {
      Map<String,String> args = new HashMap<String,String>();
      args.put("maxTokenLength", "1000");
      StandardPlusTokenizerFactory factory = new StandardPlusTokenizerFactory();
      factory.setLuceneMatchVersion(BaseTokenStreamTestCase.TEST_VERSION_CURRENT);
      factory.init(args);
      try (Tokenizer stream = factory.create(reader)) {
        BaseTokenStreamTestCase.assertTokenStreamContents(stream,
            new String[] {"one", "two", "three", longWord, "four", "five", "six" });
      }
    }
  }

  @Test
  public void testStandardPlusTokenizerNihongo() throws Exception {
    try (Reader reader = new StringReader("記号☆☆日本語★あい   え う")) {
      StandardPlusTokenizerFactory factory = new StandardPlusTokenizerFactory();
      factory.setLuceneMatchVersion(BaseTokenStreamTestCase.TEST_VERSION_CURRENT);
      Map<String, String> args = Collections.emptyMap();
      factory.init(args);
      try (Tokenizer stream = factory.create(reader)) {
        BaseTokenStreamTestCase.assertTokenStreamContents(stream,
            new String[] {"記", "号",  "☆", "☆",
              "日", "本", "語",  "★",
          "あ", "い", "え", "う"
            });
      }
    }
  }

  @Test
  public void testStandardPlusTokenizerSpace() throws Exception {
    try (Reader reader = new StringReader("あ　\nい")) {
      StandardPlusTokenizerFactory factory = new StandardPlusTokenizerFactory();
      factory.setLuceneMatchVersion(BaseTokenStreamTestCase.TEST_VERSION_CURRENT);
      Map<String, String> args = Collections.emptyMap();
      factory.init(args);
      try (Tokenizer stream = factory.create(reader)) {
        BaseTokenStreamTestCase.assertTokenStreamContents(stream,
            new String[] {"あ", "い"});
      }
    }
  }
}
