(defproject standard-plus-tokenizer "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Apache License Verson 2.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.4.0"]
                 [org.apache.solr/solr-core "4.0.0"]
                 [org.apache.lucene/lucene-test-framework "4.0.0"]
                 ]
  :profiles  {:dev  {:dependencies  [
                                     [junit/junit "4.10"]
                                     ]
                     }}
  :plugins  [[lein-junit "1.0.3"]]
  :hooks  [leiningen.hooks.junit]
  :source-paths  ["src/clojure"]
  :java-source-paths   ["src/java" "test/java"]
  :junit  ["test/java"]
  :jvm-opts  ["-XX:MaxPermSize=128m"]
  )
