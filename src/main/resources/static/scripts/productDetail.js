

let hideProductSavedAlertTimer = undefined;
let hideProductAddedAlertTimer = undefined;

document.addEventListener("DOMContentLoaded", () => {
	const productLookupCodeElement = getProductLookupCodeElement();

	getProductCountElement().addEventListener("keypress", productCountKeypress);
	getProductCostElement().addEventListener("keypress", productCostKeypress);
	productLookupCodeElement.addEventListener("keypress", productLookupCodeKeypress);
	
	getSaveActionElement().addEventListener("click", saveActionClick);
	getDeleteActionElement().addEventListener("click", deleteActionClick);

	

	if (!productLookupCodeElement.disabled) {
		productLookupCodeElement.focus();
		productLookupCodeElement.select();
	}
});

function productLookupCodeKeypress(event) {
	if (event.which !== 13) { // Enter key
		return;
	}

	const productCountElement = getProductCountElement();
	productCountElement.focus();
	productCountElement.select();

	const productCostElement = getProductCostElement();
	productCostElement.focus();
	productCostElement.select();
}

function productCountKeypress(event) {
	if (event.which !== 13) { // Enter key
		return;
	}

	saveActionClick();
}
// Added a keypress for cost (Floyd Brown)
function productCostKeypress(event) {
	if (event.which !== 13) { // Enter key
		return;
	}

	saveActionClick();
}

// Save
function saveActionClick(event) {
	if (!validateSave()) {
		return;
	}

	const saveActionElement = event.target;
	saveActionElement.disabled = true;

	const productId = getProductId();
	const productIdIsDefined = ((productId != null) && (productId.trim() !== ""));
	const saveActionUrl = ("/api/product/"
		+ (productIdIsDefined ? productId : ""));
	const saveProductRequest = {
		id: productId,
		count: getProductCount(),
		cost: getProductCost(),
		lookupCode: getProductLookupCode()
	};

	if (productIdIsDefined) {
		ajaxPut(saveActionUrl, saveProductRequest, (callbackResponse) => {
			saveActionElement.disabled = false;

			if (isSuccessResponse(callbackResponse)) {
				displayProductSavedAlertModal();
			}
		});
	} else {
		ajaxPost(saveActionUrl, saveProductRequest, (callbackResponse) => {
			saveActionElement.disabled = false;

			if (isSuccessResponse(callbackResponse)) {
				displayProductSavedAlertModal();

				if ((callbackResponse.data != null)
					&& (callbackResponse.data.id != null)
					&& (callbackResponse.data.id.trim() !== "")) {

					document.getElementById("deleteActionContainer").classList.remove("hidden");

					setProductId(callbackResponse.data.id.trim());
				}
			}
		});
	}
};

function validateSave() {
	const lookupCode = getProductLookupCode();
	if ((lookupCode == null) || (lookupCode.trim() === "")) {
		displayError("Please provide a valid product lookup code.");
		return false;
	}

	const count = getProductCount();
	if ((count == null) || isNaN(count)) {
		displayError("Please provide a valid product count.");
		return false;
	} else if (count < 0) {
		displayError("Product count may not be negative.");
		return false;
	}
//---------------------------------------------------------------
// I did for cost what was already done for count(Floyd)
	const cost = getProductCost();
	if ((cost == null) || isNaN(cost)) {
		displayError("Please provide a valid product cost.");
		return false;
	} else if (cost < 0) {
		displayError("Product cost may not be negative.");
		return false;
	}

	return true;
}
//------------------------------------------------------------------

function displayProductSavedAlertModal() {
	if (hideProductSavedAlertTimer) {
		clearTimeout(hideProductSavedAlertTimer);
	}

	const savedAlertModalElement = getSavedAlertModalElement();
	savedAlertModalElement.style.display = "none";
	savedAlertModalElement.style.display = "block";

	hideProductSavedAlertTimer = setTimeout(hideProductSavedAlertModal, 1200);
}

function hideProductSavedAlertModal() {
	if (hideProductSavedAlertTimer) {
		clearTimeout(hideProductSavedAlertTimer);
	}

	getSavedAlertModalElement().style.display = "none";
}

function deleteActionClick(event) {
	const deleteActionElement = event.target;
	const deleteActionUrl = ("/api/product/" + getProductId());

	deleteActionElement.disabled = true;

	ajaxDelete(deleteActionUrl, (callbackResponse) => {
		deleteActionElement.disabled = false;

		if (isSuccessResponse(callbackResponse)) {
			window.location.replace("/");
		}
	});
};
//-------------------------------------------------------------------
// new function that is based off of saved alert modal (Floyd)
// I think that these can be used to build something better later on, maybe something like a function that is for when the cart 
// gets clicked on, and this is part of what happens/ can go inside. I just can not think of a way to do more than this towards that though
function displayProductAddedAlertModal() {
		if (hideProductAddedAlertTimer) {
			clearTimeout(hideProductAddedAlertTimer);
		}
	
		const addedCartAlertModalElement = getAddedCartAlertModalElement();
		addedCartAlertModalElement.style.display = "none";
		addedCartAlertModalElement.style.display = "block";
	
		hideProductAddedAlertTimer = setTimeout(hideProductAddedCartAlertModal, 1200);
	}
// this function is based off of hide product saved alert modal	
	function hideProductAddedCartAlertModal() {
		if (hideProductAddedAlertTimer) {
			clearTimeout(hideProductAddedAlertTimer);
		}
	// created this getter below 
		getAddedCartAlertModalElement().style.display = "none";
	}

//-----------------------------------------------------------------
function completeSaveAction(callbackResponse) {
	if (callbackResponse.data == null) {
		return;
	}

	if ((callbackResponse.data.redirectUrl != null)
		&& (callbackResponse.data.redirectUrl !== "")) {

		window.location.replace(callbackResponse.data.redirectUrl);
		return;
    }
}


//----------------------------------------------------------
// Getters and setters
function getSaveActionElement() {
	return document.getElementById("saveButton");
}

function getSavedAlertModalElement() {
	return document.getElementById("productSavedAlertModal");
}

function getDeleteActionElement() {
	return document.getElementById("deleteButton");
}


//------------------------------------------------------------------------
// (added by Floyd Brown)
function getAddedCartAlertModalElement() {
	return document.getElementById("productAddedCartAlertModal");
}
//---------------------------------------


function getProductId() {
	return getProductIdElement().value;
}
function setProductId(productId) {
	getProductIdElement().value = productId;
}
function getProductIdElement() {
	return document.getElementById("productId");
}

function getProductLookupCode() {
	return getProductLookupCodeElement().value;
}
function getProductLookupCodeElement() {
	return document.getElementById("productLookupCode");
}

function getProductCount() {
	return Number(getProductCountElement().value);
}
function getProductCountElement() {
	return document.getElementById("productCount");
}
//--------------------------------------------------------
// Added a getter cost element (Floyd) based off of count element
function getProductCost() {
	return Number(getProductCostElement().value);
}
function getProductCostElement() {
	return document.getElementById("productCost");
}
//----------------------------------------------------------------



