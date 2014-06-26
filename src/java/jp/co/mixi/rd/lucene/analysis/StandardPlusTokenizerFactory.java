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

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.io.Reader;
import java.util.Map;

/**
 * Factory for {@link StandardPlusTokenizer}.
 * <pre class="prettyprint">
 * &lt;fieldType name="text_stndrd_plus" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="jp.co.mixi.rd.lucene.analysis.StandardPlusTokenizerFactory" maxTokenLength="255"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 */
public class StandardPlusTokenizerFactory extends TokenizerFactory {
  private final int maxTokenLength;

  /** Creates a new StandardTokenizerFactory */
  public StandardPlusTokenizerFactory(Map<String,String> args) {
    super(args);
    assureMatchVersion();
    maxTokenLength = getInt(args, "maxTokenLength", StandardAnalyzer.DEFAULT_MAX_TOKEN_LENGTH);
    if (!args.isEmpty()) {
      throw new IllegalArgumentException("Unknown parameters: " + args);
    }
  }

  public StandardPlusTokenizer create(AttributeFactory factory, Reader input) {
    StandardPlusTokenizer tokenizer = new StandardPlusTokenizer(luceneMatchVersion, factory, input);
    tokenizer.setMaxTokenLength(maxTokenLength);
    return tokenizer;
  }
}
