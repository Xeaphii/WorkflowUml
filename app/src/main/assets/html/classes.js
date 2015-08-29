//Show UCLA CS class dependencies (not complete)
$(document).ready(function() {
    
    var g = new Graph();
    g.edgeFactory.template.style.directed = true;
  
	  var init = function(r, n) {
				/* the Raphael set is obligatory, containing all you want to display */
				var set = r.set().push(
					/* custom objects go here */
					
					r.rect(n.point[0]-30, n.point[1]-13, 60, 44).attr({"fill": "#d0d0d0", r : "0px", "stroke-width" : n.distance == 0 ? "3px" : "1px" })).push(
					r.text(n.point[0], n.point[1] + 10, (n.label || n.id) ));
				return set;
			};
			
			var loopShapeLarge = function(r, n) {
				/* the Raphael set is obligatory, containing all you want to display */
				var set = r.set().push(
					/* custom objects go here */
					
					r.rect(n.point[0]-30, n.point[1]-13, 150, 44).attr({"fill": "#FFCC00", r : "20px", "stroke-width" : n.distance == 0 ? "3px" : "1px" })).push(
					r.text(n.point[0]+40, n.point[1] + 10, (n.label || n.id).substring(0,25)+"\n"+ (n.label || n.id).substring(25,(n.label || n.id).length)));
					
			return set;
			};
			var loopShape = function(r, n) {
				/* the Raphael set is obligatory, containing all you want to display */
				var set = r.set().push(
					/* custom objects go here */
					
					r.rect(n.point[0]-30, n.point[1]-13, 150, 44).attr({"fill": "#FFCC00", r : "20px", "stroke-width" : n.distance == 0 ? "3px" : "1px" })).push(
					r.text(n.point[0]+40, n.point[1] + 10, (n.label || n.id)));
					
			return set;
			};
			
	
	data=  getURLParameters("string_input");
	ActivitiesArray = data.split(",");
	var width = (ActivitiesArray.length/8)*$(document).width();
	isPreviousIf = 0;
	g.addNode("0:Init", {render:init});
    var height = $(document).height();
		for (i = 1; i < ActivitiesArray.length; i++) { 
		
			SubActivities = ActivitiesArray[i].split("/");
			if(SubActivities.length>1){
				g.addEdge("".concat((i-1),":",ActivitiesArray[i-1]),"".concat(0,":",SubActivities[0]));
				g.addEdge("".concat((i-1),":",ActivitiesArray[i-1]),"".concat(1,":",SubActivities[1]));
				isPreviousIf = 1;
			}else{
				if(isPreviousIf == 1 && ActivitiesArray[i-1].split("/").length>1){
				if(ActivitiesArray[i].toLowerCase().indexOf("repeat")>-1){
				if("".concat(i,":",ActivitiesArray[i]).length>25){
					g.addNode("".concat(i,":",ActivitiesArray[i]), {render:loopShapeLarge});
				}else{
					g.addNode("".concat(i,":",ActivitiesArray[i]), {render:loopShape});
				}
						

					}
					g.addEdge("".concat(0,":",ActivitiesArray[i-1].split("/")[0]),"".concat(i,":",ActivitiesArray[i]));
					g.addEdge("".concat(1,":",ActivitiesArray[i-1].split("/")[1]),"".concat(i,":",ActivitiesArray[i]));
					isPreviousIf = 0
				}else{
					//g.addNode("".concat(i,":",ActivitiesArray[i]), {render:render});
					if(ActivitiesArray[i].toLowerCase().indexOf("repeat")>-1){
						
							if("".concat(i,":",ActivitiesArray[i]).length>25){
											g.addNode("".concat(i,":",ActivitiesArray[i]), {render:loopShapeLarge});
											}else{
											g.addNode("".concat(i,":",ActivitiesArray[i]), {render:loopShape});
											}
					}
					g.addEdge("".concat((i-1),":",ActivitiesArray[i-1]),"".concat(i,":",ActivitiesArray[i]));
				}	
			}
		}

    var layouter = new Graph.Layout.Ordered(g, topological_sort(g));
    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
	
});
 function getURLParameters(paramName) 
  {
   var sURL = window.document.URL.toString();
   
   if (sURL.indexOf("?") > 0)
   {
    var arrParams = sURL.split("?"); 
     
    var arrURLParams = arrParams[1].split("&");
    
    var arrParamNames = new Array(arrURLParams.length);
    var arrParamValues = new Array(arrURLParams.length);
    
    var i = 0;
    for (i=0;i<arrURLParams.length;i++)
    {
     var sParam =  arrURLParams[i].split("=");
     arrParamNames[i] = sParam[0];
     if (sParam[1] != "")
      arrParamValues[i] = unescape(sParam[1]);
     else
      arrParamValues[i] = "No Value";
    }
    
    for (i=0;i<arrURLParams.length;i++)
    {
        if(arrParamNames[i] == paramName){
      //alert("Param from activity:"+arrParamValues[i]);
      return arrParamValues[i];
      }
    }
    return "No Parameters Found";
   }
   
 }	
