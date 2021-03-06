(ns leiningen.new.pure-clj
  "Generates a pure-clj project"
  (:require [clojure.string :as s]
            [leiningen.core.main :as main]
            [leiningen.new.templates :refer [renderer name-to-path ->files
                                             year project-name multi-segment
                                             sanitize-ns]]))

(def render (renderer "pure-clj"))

(defn capitalize-ns [namespace]
  (->> (s/split namespace #"\.")
       (map s/capitalize)
       (s/join ".")))

(defn project-file [app?]
  (if app?
    "project_app.clj"
    "project_lib.clj"))

(defn psc-package-file [app?]
  (if app?
    "psc-package_app.json"
    "psc-package_lib.json"))

(defn purs-entry [app? data]
  (if app?
    ["src/{{sanitized}}/Main.purs" (render "main.purs" data)]
    ["src/{{sanitized}}/Core.purs" (render "core.purs" data)]))

(defn pure-clj
  "Generates a Purescript (Clojure backend) project.
  Ex.: lein new pure-clj myproject `type`
  => generates a pure-clj project names myproject where type can be `app` or `lib` (default)"
  [name & [type]]
  (let [app? (= type "app")
        main-ns (multi-segment (sanitize-ns name) (if app? "main._core" "core._core"))
        data {:raw-name name
              :name (project-name name)
              :namespace (capitalize-ns main-ns)
              :sanitized (capitalize-ns (name-to-path name))
              :purs-name (capitalize-ns (project-name name))
              :year (year)}]
    (main/info "Generating a pure-clj project called" name)
    (->files data
             ["project.clj" (render (project-file app?) data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["psc-package.json" (render (psc-package-file app?) data)]
             (purs-entry app? data)
             "src-gen"
             "src-clj"
             "resources")
    (main/info "Make sure you have purs, pursclj and psc-package installed and in your path")))
