## JADE

#### DESCRIPTION

Launching jade main container, subsequent containers within main container and launch multiple agents within those containers from eclipse/JVM. 


### Instructions

Go to pom.xml and find the profiles section where you can find two profiles 

##### a) jade-main 

	mainClass => jade.Boot
	argument1 => conf
	argument1 => src/main/resources/jade-main-container.properties

##### b) jade-spawn 

	mainClass => jade.Boot
	argument1 => conf
	argument1 => src/main/resources/jade-spawn-container.properties


#### DESCRIPTION

**"exercise", "still", "talk"** are the 3 different types of agents that will be launched from a spawning agent initiated after the main conatiner is up.

All three agents do very limited task as of now. I will add the mechanism to interact between agents soon. But for now it can be realized as a simple mechanism to work with jade for beginners. Just run the two profiles using either console or run configurations provided by ECLIPSE-IDE and you`ll see that 

#### Using Console

**mvn exec:java -Pjade-main**

**mvn exec:java -Pjade-spawn**

###### Details on Properties file 
{<br /> 
Main-Container 
>gui=true<br />
host=localhost<br />
port=10099 **By default its 1099**<br />
local-port=10099<br />
jade_domain_df_autocleanup=true 
**Yellow pages Service Provider**

}


{<br /> Spawn Agent <br />
>agents=\<br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;spawn:com.elsa.jade.testing.SpawnAgent<br />
port=10099 **By default its 1099**<br />
host=localhost<br />
main=false<br />
no-display=true<br />

}