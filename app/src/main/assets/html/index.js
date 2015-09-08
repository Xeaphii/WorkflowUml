var width = window.innerWidth
		|| document.documentElement.clientWidth
		|| document.body.clientWidth;
	//	width= width -100;
var height = window.innerHeight
		|| document.documentElement.clientHeight
		|| document.body.clientHeight;

var isIfElse = 0;
window.onload = function() {
	data=  getURLParameters("string_input").substr(1, getURLParameters("string_input").length-1);
	ActivitiesArray = data.split(",");
	height = ((ActivitiesArray.length+1)/2)*height;
	
	

    var paper = new Raphael(document.getElementById('canvas_container'), width, height);
	//DrawActivity(paper,0);

	//DrawActivities(paper,0);
	DrawElipse(paper,0);
		//Loop for activities
	offset = 0;
	for (j = 0; j < ActivitiesArray.length; j++) { 
		if(ActivitiesArray[j].toLowerCase().indexOf("repeat")>-1){
			//i
			//DrawIfElseCondition(paper,j+1+offset,ActivitiesArray[j]);
			DrawLoopActivities(paper,j+1+offset,ActivitiesArray[j]);
			if(isIfElse == 1){
				isIfElse = 0;
			}
		}
		else if(ActivitiesArray[j].toLowerCase().indexOf("if")>-1){
			if(ActivitiesArray[j].split("/").length>1){
				DrawIfElseCondition(paper,j+1+offset,ActivitiesArray[j]);
				
			}else{
				DrawIfCondition(paper,j+1+offset,ActivitiesArray[j]);

			}
			offset  += 1;
		}else if(ActivitiesArray[j].split("+").length>1){
			DrawActivities(paper,j+1+offset,ActivitiesArray[j]);
			if(isIfElse == 1){
				isIfElse = 0;
			}
		}else{
			
			DrawActivity(paper,j+1+offset,ActivitiesArray[j]);
			if(isIfElse == 1){
				isIfElse = 0;
			}
		}
		
	
	}
	

}
function DrawElipse(paper,y){
//	if(isIfElse == 0){
     //	var d = "M "+(width/2)+","+(-40+100*y)+" L "+(width/2)+","+(10+100*y);
     //	paper.path(d);
     //	}
	paper.ellipse(50, 25, 50, 25).translate(width/2-50,10+100*y).attr({fill: '#D4FF5F'});
	paper.text(width/2, 35+100*y, ReformatText("START")).attr({fill: '#000000',"font-size": 14, "font-family": "Arial, Helvetica, sans-serif"});
	
}
function DrawLoopActivities(paper,y,ActText)
{
	SubActivitiesArrayT = ActText.split("-");
	
	SubActivitiesArray = SubActivitiesArrayT[1].split("+");
		paper.text(width/2+ 110*(SubActivitiesArray.length)-55*SubActivitiesArray.length+55, 100*y+40, ReformatText(SubActivitiesArrayT[0]+" times")).attr({fill: '#000000',"font-size": 12, "font-family": "Arial, Helvetica, sans-serif"});

	paper.rect(0, 0, 110*SubActivitiesArray.length, 60,8).translate(width/2-55*SubActivitiesArray.length,5+100*y).attr({fill: '#D4FF5F'});
	if(SubActivitiesArray.length%2 == 0){
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp < Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawSubActivity(paper,y,iTemp+.5,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}else{
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp <= Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawSubActivity(paper,y,iTemp,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}
}


function DrawIfElseCondition(paper,y,ActText){
	SubActivitiesArray = ActText.split("-");
	DrawCondition(paper,y,SubActivitiesArray[0]);
	
	SubActivitiesArrayT = SubActivitiesArray[1].split("/");
	DrawCondActivities(paper,y+1,-3,SubActivitiesArrayT[0]);
	DrawCondActivities(paper,y+1,3,SubActivitiesArrayT[1]);
	isIfElse = 1;
}
function DrawCondActivities(paper,y,offsetT,ActText)
{
	SubActivitiesArray = ActText.split("+");
	if(offsetT>0){
			paper.text((width+offsetT*110)/2, 100*y-30, ReformatText("IF NOT")).attr({fill: '#000000',"font-size": 12, "font-family": "Arial, Helvetica, sans-serif"});

	}else{
			paper.text((width+offsetT*110)/2, 100*y-30, ReformatText(" IF YES")).attr({fill: '#000000',"font-size": 12, "font-family": "Arial, Helvetica, sans-serif"});

	}
	var d = "M "+(width/2)+","+(-40+100*y)+" L "+(width/2+offsetT*110)+","+(10+100*y);
	paper.path(d);
	paper.rect(0, 0, 110*SubActivitiesArray.length, 60).translate(width/2-55*SubActivitiesArray.length+offsetT*110,5+100*y).attr({fill: '#c0c0c0'});
	
	if(SubActivitiesArray.length%2 == 0){
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp < Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawCondSubActivity(paper,y,iTemp+.5+offsetT,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}else{
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp <= Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawCondSubActivity(paper,y,iTemp+offsetT,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}
	var d = "M "+(width/2)+","+(100*y+50+60)+" L "+(width/2+offsetT*110)+","+(10+100*y+55);
	paper.path(d);
}
function DrawCondSubActivity(paper,y,number,ActText)
{
	paper.rect(0, 0, 100, 50).translate(width/2-50 + 110*number,10+100*y).attr({fill: '#9cf'});
	paper.text(width/2 + 110*number, 35+100*y, ReformatText(ActText)).attr({fill: '#000000'});
}


function DrawIfCondition(paper,y,ActText){
	
	SubActivitiesArray = ActText.split("-");
	DrawCondition(paper,y,SubActivitiesArray[0]);

	paper.text(width/2+20, 100*y+80, ReformatText(" IF YES")).attr({fill: '#000000',"font-size": 12, "font-family": "Arial, Helvetica, sans-serif"});
	if(isIfElse == 1){
    					isIfElse = 0;
    				}
	DrawActivities(paper,y+1,SubActivitiesArray[1]);
}

function DrawCondition(paper,y,ActText)
{
	if(isIfElse == 0){
	var LengthLine = "M "+(width/2)+","+(-40+100*y)+" L "+(width/2)+","+(10+100*y);
	paper.path(LengthLine);
	}
	var d = "M "+(width/2-75)+","+(35+100*y)+" L "+(width/2)+","+(10+100*y)+" L "+(width/2+75)+","+(35+100*y)+" L "+(width/2)+","+(60+100*y)+" L "+(width/2-75)+","+(35+100*y)+"";
	 var condition = paper.path(d);
	 condition.attr("fill", "#E8E053");
	 paper.text(width/2, 35+100*y, ReformatText(ActText)).attr({fill: '#000000',"font-size": 10, "font-family": "Arial, Helvetica, sans-serif"});
}

function DrawActivity(paper,y,ActText)
{
	if(isIfElse == 0){
	var d = "M "+(width/2)+","+(-40+100*y)+" L "+(width/2)+","+(10+100*y);
	paper.path(d);
	}
	paper.rect(0, 0, 100, 50).translate(width/2-50,10+100*y).attr({fill: '#9cf'});
	paper.text(width/2, 35+100*y, ReformatText(ActText)).attr({fill: '#000000'});
}
function DrawSubActivity(paper,y,number,ActText)
{
	if(isIfElse == 0){
	var d = "M "+(width/2)+","+(-40+100*y)+" L "+(width/2)+","+(10+100*y);
	paper.path(d);
	}
	paper.rect(0, 0, 100, 50).translate(width/2-50 + 110*number,10+100*y).attr({fill: '#9cf'});
	paper.text(width/2 + 110*number, 35+100*y, ReformatText(ActText)).attr({fill: '#000000'});
}


function DrawActivities(paper,y,ActivitiesText)
{
	SubActivitiesArray = ActivitiesText.split("+");
	paper.rect(0, 0, 110*SubActivitiesArray.length, 60).translate(width/2-55*SubActivitiesArray.length,5+100*y).attr({fill: '#c0c0c0'});
	
	if(SubActivitiesArray.length%2 == 0){
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp < Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawSubActivity(paper,y,iTemp+.5,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}else{
		for(iTemp = Math.ceil(-SubActivitiesArray.length/2) ; iTemp <= Math.floor(SubActivitiesArray.length/2);iTemp++){
			DrawSubActivity(paper,y,iTemp,SubActivitiesArray[iTemp+Math.floor(SubActivitiesArray.length/2)]);
		}
	}
	
	
}


function ReformatText(input){
	output = "";
	if(input.length>20){
		i = 0;
		for (; i < input.length; i = i+20) {
			output = output+input.substr(i,20)+"\n";
		}
		if(input.length%20>0){
			output = output+input.substr(i,(input.length%20));
		}
	}else{

		output = input;
	}
	return output;
}

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
