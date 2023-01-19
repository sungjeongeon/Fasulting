import HospitalList from "../../components/List/HospitalList";
import MainCategoryList from "../../components/Category/MainCategoryList";
import SubCategoryList from "../../components/Category/SubCategoryList";

function PsList() {
  return (
    <div>
      <MainCategoryList />
      <SubCategoryList />
      <HospitalList />
    </div>
  );
}

export default PsList;
