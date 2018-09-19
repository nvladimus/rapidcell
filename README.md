## RapidCell
a hybrid model of E.coli chemotaxis

### Program scope
RapidCell is designed for large-scale simulations of chemotaxis performed by bacterium *E. coli*. Single-cell simulation is ok, too.

No fancy graphic, but detailed output about each bacterium at any moment. Works under all operating systems.

The simulation results are saved as .txt files for analysis in your favourite program.

![Chemotaxis pathway](/img/network.gif)

### Features
* Simulation of cells swimming in 2D with realistic run and tumble times, and with effect of rotational diffusion.

* Simultaneous running of up to 1,000,000 cells on your desktop (no need for parallel/super computer).

* Model reproduces the high sensitivity of the chemotactic pathway (FRET experiments).

* Multiple flagellar motors are simulated explicitly (!), and their number affect run and tumble times.

* Gradient of attractant can be chosen from 8 pre-defined shapes, or defined by user.

* Composition of receptor cluster (Tar and Tsr receptors) can be defined by user.

* Protein levels (CheA, CheR, CheB, CheY) can be altered to simulate mutants.

* Runs on any operational system (tested on Windows XP, Suse Linux, Mac OS X).

Source code is simple and open.

### Installation
Unpack RapidCell into your home folder. If you are Windows user, simply double click on `RapidCellwin.exe`. In other operating systems, open the terminal window. 
Make sure Java is installed in your system (check Java here). 

Using the terminal window, go to the RapidCell directory

```cd YOUR_HOME_FOLDER/RapidCell1.4.2/```

Launch the precompiled binaries (*.class files):

```java -cp ./bin Run```

Set the desired parameters in GUI and click *Run* button. After RapidCell is complete, the output is written into 2 txt-files (tab-delimited): 

`individuals.out`, variables of individual cells over time;

`averages.out`, variables over time, averaged over the population.

[Analysis in Python using Jupyter notebook](./analysis_demo.ipynb)

### Compilation from sources
The program is already compiled and runs as is. If you changed the source code, you need to re-compile it. To do this, make sure that `javac` works from command line in your current directory. If not, check that you have the [Java SE Development Kit 6 (JDK 6)](http://java.sun.com/javase/6/download.jsp) or newer (you need the **JDK**, not JRE.)

If  `javac` command still does not work after JDK installation, set up the `PATH` variable to include your JDK binaries folder (`jdk1.6.0_<version>/bin`). See more information about setting PATH in [Java Tutorial](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html).

When `javac` finally works in command line, compile the program
```javac *.java```

When done, launch RapidCell from command line:
```java -cp . Run```

### FAQ
Q: Does it simulate cell swimming in 3D?

A: RapidCell simulates swimming of cells only in 2D space, which is sufficient for many  applications. For a full 3D simulation, you may try a full-scale simulator [AgentCell](https://omictools.com/agentcell-tool), which inspired the development of RapidCell.

Q: How is the chemotaxis pathway simulated?

A: It is simulated in a hybrid way, that is neither purely by differential equations nor stochastically, but using a hybrid of both. Fast reactions are simulated by algebraic equations (ligand binding, phosphorylation of CheA and CheY), slow reactions by ordinary differential equations (receptor methylation/demethylation), and major stochastic events by stochastic methods (flagellar motor switching). This enables high performance of the program and desired accuracy. See [Vladimirov et al., 2008](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2588534/?tool=pubmed) for full model description.

Q: How accurate is it?

It accurately reproduces the average characteristics of cell response to attractant addition/removal: drop/rise of CheY-P, rate of receptor methylation and demethylation, clockwise and counter-clockwise switching times of flagellar motors, running and tumbling times of a swimming cell. Cellular parameters are taken from available literature: receptor cluster composition, number of flagellar motors per cell, cell swimming speed, rotational diffusion and the angle of cell tumbling. It is as realistic as I could make it based on available experimental data at the time of publication (2008).

### Screenshots
![Panel 1](/screenshots/screen1.png)
![Panel 2](/screenshots/screen2.png)
![Panel 3](/screenshots/screen3.png)
![Panel 4](/screenshots/screen4.png)
![Panel 5](/screenshots/screen5.png)
![Panel 6](/screenshots/screen6.png)

### References
If you use RapidCell in your work, please kindly cite the original paper:

* Vladimirov N, Lovdok L, Lebiedz D, Sourjik V, (2008). *Dependence of bacterial chemotaxis on gradient shape and adaptation rate.* [PLoS Comput Biol 4(12): e1000242](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2588534/?tool=pubmed)