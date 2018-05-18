(ns my-exercise.api-test
  (:require [clojure.test :refer :all]
            [my-exercise.api :refer :all]))

(deftest get-results-test
  (testing "for states with no upcoming elections, `get-results` return null"
    (let [parameter {:state "NY"}]
    (is (= nil (get-results parameter)))))
  (testing "for states with upcoming election, `get-results` return vector with expected parameters"
    (let [parameter {:state "AL"}
        expected-result ["Alabama Primary Election" "06/04/2018" "http://tvote.org/2lEp2IN"]]
    (is (= (get-results parameter) expected-result)))))