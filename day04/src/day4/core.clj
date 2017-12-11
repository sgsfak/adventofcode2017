(ns day4.core)

(def passphraces (-> "resources/passphrases.txt" slurp clojure.string/split-lines))

(defn word-is-contained [word lst]
  (if (empty? lst)
     false
     (let [h (first lst)]
       (if (= h word)
          true
          (recur word (rest lst))))))



(defn is-valid [check-fn list-of-words]
  (if (empty? list-of-words) true
      (let [h (first list-of-words)
            r (rest list-of-words)
            c (check-fn h r)]
        (if c false
          (recur check-fn r)))))


(defn solution1 [passphraces]
  (let [lli (map #(clojure.string/split % #"\s") passphraces)
        filter-fn (partial is-valid word-is-contained)]
     (count (filter filter-fn lli))))


(defn normalize-word [w]
  (-> w (clojure.string/split #"")
      sort
      clojure.string/join))

(defn anagram-equal [nw w2]
  ; (println "Checking " w1 " with " w2)
  (= nw (normalize-word w2)))


(defn anagram-is-contained [word lst]
  (if (empty? lst)
     false
     (let [h (first lst)
           nh (normalize-word h)]
       (if (anagram-equal nh word)
          true
          (recur word (rest lst))))))



(defn solution2 [passphraces]
  (let [lli (map #(clojure.string/split % #"\s") passphraces)
        filter-fn (partial is-valid anagram-is-contained)]
     (count (filter filter-fn lli))))
