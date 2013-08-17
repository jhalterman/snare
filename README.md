# Snare

*For snaring rabbits and such*

A simple tool for consuming messages from a RabbitMQ queue.

## The Gist

Compile it:

```
lein uberjar
```

Run it:

```
java -jar target/snare.jar
```

Supported arguments:

```
 Switches          Default  Desc                          
 --------          -------  ----
 -f, --file                 Properties file                    
 -h, --host                 Host                         
 --port            5672     Port                          
 -u, --username             Username                      
 -p, --password             Password                      
 -v, --vhost       /        Vhost                         
 -r, --routingKey  #        Routing key to bind to queue  
 -q, --queue                Name of queue to consume from 
```

## License

Copyright © 2013 Jonathan Halterman

Distributed under the Eclipse Public License, the same as Clojure.
