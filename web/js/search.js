let visibleCount = 2; 
let allTours = []; 

function searchTour() {
    let query = document.getElementById("searchInput").value.trim();
    let resultContainer = document.getElementById("tourResults");
    let loadMoreButton = document.getElementById("loadMore");

    if (query === "") {
        resultContainer.innerHTML = "";
        loadMoreButton.style.display = "none";
        return;
    }

    fetch(`SearchTourServlet?query=${encodeURIComponent(query)}`)
        .then(response => response.json())
        .then(data => {
            allTours = data; 
            resultContainer.innerHTML = "";
            visibleCount = 2; 

            if (allTours.length === 0) {
                resultContainer.innerHTML = "<p>Không tìm thấy tour nào.</p>";
                loadMoreButton.style.display = "none";
            } else {
                displayTours();
                loadMoreButton.style.display = allTours.length > 2 ? "block" : "none";
            }
        })
        .catch(error => {
            console.error("Lỗi tìm kiếm:", error);
            resultContainer.innerHTML = "<p style='color: red;'>Đã xảy ra lỗi khi tìm kiếm tour.</p>";
        });
}

function displayTours() {
    let resultContainer = document.getElementById("tourResults");
    resultContainer.innerHTML = "";

    allTours.slice(0, visibleCount).forEach(tour => {
        let tourDiv = document.createElement("div");
        tourDiv.classList.add("tour-item");

        tourDiv.innerHTML = `
            <img src="${tour.imageUrl}" alt="${tour.title}" style="width: 80%;height:100px;margin-left:3%;">
            <h3>${tour.title}</h3>
            <p><b>Location:</b> ${tour.location}</p>
            <p><b>Duration:</b> ${tour.duration}</p>                       
            <p>Price: ${parseInt(tour.price).toLocaleString()} VND</p>
            <div>${generateBookNowButton(tour)}</div>
        `;

        resultContainer.appendChild(tourDiv);
    });
}

function loadMore() {
    visibleCount += 2;
    displayTours();

    if (visibleCount >= allTours.length) {
        document.getElementById("loadMore").style.display = "none"; 
    }
}

function generateBookNowButton(tour) {
    let userName = typeof window.userName !== "undefined" ? window.userName : ""; 

    if (userName !== "") {
        return `
            <form action="booking.jsp" method="GET">
                <input type="hidden" name="title" value="${tour.title}">
                <input type="hidden" name="location" value="${tour.location}">
                <input type="hidden" name="price" value="${tour.price}">
                <input type="hidden" name="duration" value="${tour.duration}">
                <input type="hidden" name="description" value="${tour.description}">
                <input type="hidden" name="imageUrl" value="${tour.imageUrl}">
                <input type="hidden" name="id" value="${tour.id}">
                <input type="hidden" name="accname" value="${userName}">
                <button class="booknow" type="submit">Book Now</button>
            </form>
        `;
    } else {
        return `<a href="login.jsp" class="booknowa">Book Now</a>`;
    }
}
