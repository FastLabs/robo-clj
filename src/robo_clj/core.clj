(ns robo-clj.core
  (:gen-class))


(defn x []
  (+ 1 2))

(defn simple
  []
  (let [vals [1 2 3]]
    (map #(str "(->" %) vals)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
