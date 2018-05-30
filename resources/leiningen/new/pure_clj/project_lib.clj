(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: add description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :source-paths ["src" "src-clj" "src-gen"]
  :plugins [[lein-shell "0.5.0"]]
  :repl-options {:init-ns {{namespace}}}
  :aliases {"pursclj" ["shell" "pursclj" "compile"
                       "clj-output/**/*.purs"
                       ".psc-package/**/src/**/*.purs"
                       "-o" "src-gen"]})
