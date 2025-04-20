/*
Lấy dữ liệu về tỉnh/thành , quận/huyện , phường/xã từ file Json

let cities = document.getElementById("city");
let districts = document.getElementById("district");
let wards = document.getElementById("ward");

let Parameter = {
    url: "./data/vietnam.json", //Đường dẫn đến file chứa dữ liệu hoặc api do backend cung cấp
    method: "GET", //do backend cung cấp
    responseType: "application/json", //kiểu Dữ liệu trả về do backend cung cấp
};

//gọi ajax = axios => nó trả về cho chúng ta là một promise
let promise = axios(Parameter);

//Xử lý khi request thành công
promise.then(function (result) {
    renderCity(result.data); // => trả về dữ liệu các tỉnh / thành phố
});

//Xử lý khi request thất bại
promise.catch(error =>{
    console.log(error);
});

function renderCity(data) {

    for (const x of data) {
        cities.options[cities.options.length] = new Option(x.Name, x.Id);
    }

    // Cập nhật plugin nice-select
    $('#city').niceSelect('update');

    // xứ lý khi thay đổi tỉnh thành thì sẽ hiển thị ra quận huyện thuộc tỉnh thành đó
    cities.onchange = function () {
        districts.length = 1;
        wards.length = 1;
        if (this.value != "") {
            const result = data.filter(n => n.Id === this.value);

            for (const k of result[0].Districts) {
                district.options[district.options.length] = new Option(k.Name, k.Id);
            }
        }
        // Cập nhật plugin nice-select
        $('#district').niceSelect('update');
        $('#ward').niceSelect('update');
    };

    // xứ lý khi thay đổi quận huyện thì sẽ hiển thị ra phường xã thuộc quận huyện đó
    districts.onchange = function () {
        wards.length = 1;
        const dataCity = data.filter((n) => n.Id === cities.value);
        if (this.value != "") {
            const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

            for (const w of dataWards) {
                wards.options[wards.options.length] = new Option(w.Name, w.Id);
            }
        }
        // Cập nhật plugin nice-select
        $('#ward').niceSelect('update');
    };

    //Gọi plugin niceSelect
    $('select').niceSelect();

    //Thêm class 'has-scrollbar' vào thẻ ul của các select hiển thị scrollbar nếu có nhiều dữ liệu
    $('#city ul').addClass('has-scrollbar');
    // $('#city ul, #district ul, #ward ul').addClass('has-scrollbar');

}
*/
