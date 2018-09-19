Welcome to rapid and accurate simulations of bacterial chemotaxis

### Troubleshooting
If you try to simulate 1,000,000 cells and you get an error 
```java.lang.OutOfMemoryError: Java heap space ```, 
try to run java with the appropriate memory allocation arguments (e.g. min 32 Mb, max 512 Mb):
    ``` $ java -Xms32m -Xmx512m -cp ./bin Run ```

### Features added in this version (1.4)
* CheA autophosphorylation is based on steady-state equations. The fraction of active CheA in adapted state is 0.0164, in agreement with [Sourjik, Berg, 2002, suppl.](http://www.pnas.org/content/99/1/123)
* `P_on`, the probability of receptor cluster to be ON, is set to 1/3, as suggested by FRET experiments (attractant addition causes about twice less response than attractant removal)
* `P_on` is available for output
* Most parameters of the network can be changed using GUI.
* Design was improved and simple graphical output added for convenient control of initial and final cell positions on arena.

### Current limitations
* Cells are simulated in 2D space, and it is designed intentionally to avoid excessive computation.
* CheB activity is assumed constant. In reality, CheB has positive feedback from CheA activity via phosphorylation, so the system response (CheYp) 
adapts to negative stimuli faster than to positive ones. This presumably reduces tumbling time of a cell in escape response. The current model is
 accurate for positive responses, such as swimming in attractants.
* Accordingly, only attractants are currently simulated by the program. Repellents or temperature gradients are no implemented, but can be added by motivated users ;)
