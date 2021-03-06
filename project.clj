(defproject standard-plus-tokenizer "4.10.3"
  :min-lein-version "2.2.0"
  :description "An extension of Lucene's StandardTokenizer. This tokenizer tokenizes all characters but spaces. (StandardTokenizer doesn't tokenizes some characters: Symbol, Punctuation, ...)"
  :url "https://github.com/haruyama/StandardPlusTokenizer"
  :license {:name "Apache License Verson 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.apache.solr/solr-core "4.10.3"]
                 [org.apache.lucene/lucene-test-framework "4.10.3"]]
  :profiles  {:dev  {:dependencies  [[junit/junit "4.12"]] }}
  :plugins  [[lein-junit "1.1.2"]]
  :hooks  [leiningen.hooks.junit]
  :source-paths  ["src/clojure"]
  :java-source-paths   ["src/java" "test/java"]
  :junit  ["test/java"]
  :jvm-opts  ["-XX:MaxPermSize=128m"]
  :jar-exclusions [#"Test"])
