window.onload = resizeBanner;
window.onresize = resizeBanner;

function resizeBanner()
{
	if (document.body.clientWidth == 0) return;

	var oBanner= document.all.item("nsbanner");
	var oText = document.all.item("nstext");

	if (oText == null) return;

	var oBannerRow1 = document.all.item("bannerrow1");
	if (oBannerRow1 != null)
	{
		var iScrollWidth = bodyID.scrollWidth;
		try { oBannerRow1.style.marginRight = 0 - iScrollWidth; }
		catch (e) { return; }
	}

	if (oBanner != null)
	{
		try
		{
			document.body.scroll = "no";
			oText.style.overflow = "auto";
 			oBanner.style.width = document.body.offsetWidth - 2;
			oText.style.paddingRight = "20px";
			oText.style.width = document.body.offsetWidth - 4;
			oText.style.top = 0;
			
			if (document.body.offsetHeight > oBanner.offsetHeight + 4)
    			oText.style.height = document.body.offsetHeight - (oBanner.offsetHeight + 4);
			else 
				oText.style.height = 0;
				
		} catch (e) { return; }
	}	
}
