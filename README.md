# Cloud Implementation of Extreme Learning Machine for Hyperspectral Image Classification
The Code for "Cloud Implementation of Extreme Learning Machine for Hyperspectral Image Classification". []
```
J. M. Haut, S. Moreno-Álvarez, E. Moreno-Avila, Victor A. Ayma, R. Pastor-Vargas and M. E. Paoletti.
Cloud Implementation of Extreme Learning Machine for Hyperspectral Image Classification
DOI:
July 2023.
```

![CLOUDELM](./images/CLOUDELM.jpg)


### Compile Java project

## Local version
```
cd ./local_elm_version
mvn clean compile assembly:single
mvn clean compile package
```

## Distributed version
```
cd ./distributed_elm_version
mvn clean compile assembly:single
mvn clean compile package
```


### Run code

## Local version
```
#!/bin/bash
java  -Xms14096m -Xms14096m -Xmx15144m -jar archivo.jar

```
## Distributed version
```
#!/bin/bash
pig -f s3://dataelm3/executions/elmtrainIP.pig -param n_hidden=900 -param input="s3://dataelm3/datasetparts_10per/" -param seed=32
pig -f s3://dataelm3/executions/elmtestIP.pig -param n_hidden=900 -param input="s3://dataelm3/TE_BIP_dataset_10TPR_.csv" -param seed=32 -param output="s3://dataelm3/test_900N_10per"
```

Reference code of local version: https://github.com/ExtremeLearningMachines/ELM-JAVA/
