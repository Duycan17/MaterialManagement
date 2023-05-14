/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author duy
 */
public class Material {

    private int materialId;
    private int statusId;
    private String materialName;
    private int materialNumber;
    private String statusName;

    public Material() {
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Material(int materialId, int statusId, String materialName, int materialNumber, String statusName) {
        this.materialId = materialId;
        this.statusId = statusId;
        this.materialName = materialName;
        this.materialNumber = materialNumber;
        this.statusName = statusName;
    }

    public Material(int materialId, int statusId, String materialName, int materialNumber) {
        this.materialId = materialId;
        this.statusId = statusId;
        this.materialName = materialName;
        this.materialNumber = materialNumber;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(int materialNumber) {
        this.materialNumber = materialNumber;
    }

    @Override
    public String toString() {
        return "Material{" + "materialId=" + materialId + ", statusId=" + statusId + ", materialName=" + materialName + ", materialNumber=" + materialNumber + '}';
    }

}
