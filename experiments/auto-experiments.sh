#!/bin/bash
## Begin on experiments folder
cd exp1         # SELECT EXPERIMENT

for dir in */; do
    echo "EXPERIMENT $dir"
    cd $dir
    rm results.txt    
    totalTime=0
    pointTime=0
    best=0
    N=25 ## Number of repetitions of the test
    echo "Reapeating experiment $N times -- Please wait"
    for i in $(seq 1 1 $N);do
        java -jar ../../../MaximizationProblem.jar input.txt >/dev/null

        line=$(cat solutions.txt | tail -3 | head -1)
        read -ra aux <<< "$line" 
        a=${aux[2]}
        totalTime=$(python3 -c "print( $totalTime + ($a / $N ) )")

        line=$(cat solutions.txt | tail -2 | head -1)
        read -ra aux <<< "$line" 
        a=${aux[2]}
        pointTime=$(python3 -c "print( $pointTime + ($a / $N ))")

        line=$(cat solutions.txt | tail -1 | head -1)
        read -ra aux <<< "$line" 
        a=${aux[3]}
        best=$(python3 -c "print( $best + ($a / $N ))")

    done
    # python3 -c "print($totalTime / $N)" >> results.txt
    # python3 -c "print($pointTime / $N)" >> results.txt
    # python3 -c "print($best / $N)" >> results.txt
    echo $totalTime >> results.txt
    echo $pointTime >> results.txt
    echo $best >> results.txt
    
    echo "Measured results saves in results.txt file:"
    echo "------------------------------"
    cat results.txt
    echo ""
    cd ..
done
