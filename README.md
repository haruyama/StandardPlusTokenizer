# StandardPlusTokenizer

An extension of Lucene's StandardTokenizer.

This tokenizer tokenizes all characters but spaces.
(StandardTokenizer does not tokenize some kinds of characters: Symbol, Punctuation, ...)

* [春山 征吾のくけー : Anuenue-0.8.1 と StandardPlusTokenizer - livedoor Blog（ブログ）](http://haruyama.blog.jp/archives/51922530.html)

## Distribution

[http://sourceforge.net/projects/anuenue/files/StandardPlusTokenizer/](http://sourceforge.net/projects/anuenue/files/StandardPlusTokenizer/)

## Building

* Install [technomancy/leiningen · GitHub](https://github.com/technomancy/leiningen) Version 2.
* lein jar

* If you modify .flex, you should get [JFlex](http://jflex.de/) and convert .flex to .java.
  * java -cp .../jflex-1.5.0.jar jflex.Main src/java/jp/co/mixi/rd/lucene/analysis/StandardPlusTokenizerImpl.jflex

## Usage

Put StandardPlusTokenizer's jar to solr collection's lib directory, such as solr/collection1/lib.

## solrconfig.xml sample

    <fieldType name="text_cjk_plus" class="solr.TextField" positionIncrementGap="100">
      <analyzer>
        <tokenizer class="jp.co.mixi.rd.lucene.analysis.StandardPlusTokenizerFactory"/>
        <filter class="solr.CJKWidthFilterFactory"/>
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.CJKBigramFilterFactory"/>
        <filter class="solr.WordDelimiterFilterFactory" preserveOriginal="1"/>
      </analyzer>
    </fieldType>

## License

Copyright © HARUYAMA Seigo

Distributed under the Apache License Version 2.0, the same as Apache Lucene / Solr.
