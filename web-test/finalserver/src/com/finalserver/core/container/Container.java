package com.finalserver.core.container;

import com.finalserver.core.container.components.Loader;
import com.finalserver.core.container.components.Logger;
import com.finalserver.core.container.components.Mapper;
import com.finalserver.core.container.components.PipeLine;

/**
 * Created by Administrator on 2017-03-06.
 */
public interface Container{ 

    public Logger getLogger(); 
    

    public void setLogger(Logger logger);

    public Loader getLoader(); 
    

    public void setLoader(Loader loader); 
    

    public Mapper getMapper();
    

    public void setMapper(Mapper mapper); 
    

    public PipeLine getPipeLine();
    

    public void setPipeLine(PipeLine pipeLine);


}