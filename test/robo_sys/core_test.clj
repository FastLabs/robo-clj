(ns robo-sys.core-test
  (:require [clojure.test :refer :all]
            [robo-sys.core :as robo]))

(deftest test-load-local-sys
  (testing "initialize sensors from resources"
    (let [sensors (robo/load-sensor "resources/robo-sys")]
      (prn sensors))))